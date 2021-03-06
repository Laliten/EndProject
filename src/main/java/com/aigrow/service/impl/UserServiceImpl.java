package com.aigrow.service.impl;

import com.aigrow.dao.PackageDao;
import com.aigrow.dao.UserDao;
import com.aigrow.model.dto.Page;
import com.aigrow.model.dto.UserDto;
import com.aigrow.model.entity.Package;
import com.aigrow.model.entity.User;
import com.aigrow.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;

/**
 * @author YangDeJian
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    @Autowired
    private PackageDao packageDao;
    /**
     * 仅限管理员使用，获取所有的管理员
     *
     * @param page
     * @return
     */
    @Override
    public List<UserDto> getAllUsers(Page page, String type) {
        String hql = "from User u where u.type="+type;
        List<User> userList = userDao.find(hql,page.getNextPage(),page.getPageSize());
        return this.e2d(userList);
    }

    /**
     * 进行登录验证，正确则返回一个dto对象，失败则返回null
     *
     * @param userDto
     * @return
     */
    @Override
    public UserDto login(UserDto userDto) {
        Map<String,Object> params = new HashMap<String,Object>();
        String username = userDto.getAccount();
        String password = userDto.getPassword();
        String type = userDto.getType();
        params.put("account",username);
        params.put("password",password);
        params.put("type",type);

        User user = userDao.get("from User c where c.account = :account and c.password = :password and c.type = :type",params);

        if(user != null){
            return this.e2d(user);
        }
        return null;
    }

    /**
     *进行注册验证，正确返回1，失败返回0
     *
     * @param userDto
     * @return
     */
    @Override
    synchronized public int register(UserDto userDto){
        if(userDto.getAccount()==null){
            return 0;
        } else {
            try {
                Map<String,Object> map = new HashMap<>(0);
                map.put("account", userDto.getAccount().trim());
                String hql = "select count(*) from User u where u.account =:account";
                if (userDao.count(hql,map) > 0){
                    throw new Exception("登录名已存在！");
                } else {
                    User user = new User();
                    user = d2e(userDto);
                    user.setType("0");
                    Serializable num = userDao.save(user);
                    if (num == null) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
                return 0;
            }
        }
    }


    /**
     * 进行注册用户名验证，若存在返回userDto，不存在返回null
     * @param account
     * @return 0||1
     */
    @Override
    public long checkUsername(String account) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("account",account);
        String hql = "select count(*) from User u where u.account=:account";
        return userDao.count(hql, params);
    }

    /**
     * 进行批量删除
     *
     * @param userIds
     */
    @Override
    public void batchDelete(String userIds) {
        if (userIds == null){
            return;
        }
        userDao.batchDelete(userIds);
    }

    /**
     * 添加用户
     *
     * @param userDto
     * @return
     */
    @Override
    public int add(UserDto userDto) {
        User user = this.d2e(userDto);
        Serializable num = userDao.save(user);
        if (num != null){
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * 更新用户信息
     *
     * @param userDto
     */
    @Override
    public UserDto update(UserDto userDto) {

        User user = userDao.get(User.class,userDto.getId());

        user.setName(userDto.getName());
        user.setAccount(userDto.getAccount());

        userDao.update(user);
        return e2d(user);
    }

    /**
     * 删除用户
     *
     * @param userId
     */
    @Override
    public void singleDelete(int userId) {
        if (userId == 0){
            return;
        }
        User user = userDao.get(User.class, userId);
        userDao.delete(user);
    }

    /**
     * 修改当前用户的密码
     *
     * @param userId
     * @param oldPwd
     * @param pwd
     * @return
     */
    @Override
    public boolean editCurrentUserPwd(int userId, String oldPwd, String pwd) {
        User u = userDao.get(User.class, userId);
        if (oldPwd.equals(u.getPassword())){
            u.setPassword(pwd);
            userDao.update(u);
            return true;
        }
        return false;
    }

    /**
     * 获取有多少用户
     * @param requestType
     * @return
     */
    @Override
    public long numOfUsers(String requestType) {
        long num = userDao.count("select count(*) from User u where u.type="+requestType);
        return num;
    }


    /**
     * 根据输入的信息搜索用户
     * @param page
     * @param searchText
     * @return
     */
    @Override
    public List<UserDto> searchUser(Page page, String searchText){

        Map<String,Object> map = new HashMap<>(0);
        map.put("searchText", searchText);

        String hql = "from User u where " +
                    " u.name=:searchText or " +
                    " u.account=:searchText or" +
                    " u.type=:searchText ";
        List<UserDto> userDtos = this.e2d(userDao.find(hql, map, page.getNextPage(), page.getPageSize()));
        if (userDtos == null || userDtos.size()==0){
            userDtos = new ArrayList<>(0);
        }
        return userDtos;
    }

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @Override
    public UserDto search(int id) {
        UserDto userDto = e2d(userDao.get(User.class,id));
        return userDto;
    }

    /**
     * 将userDto对象转换为user实体类对象
     * @param userDto
     * @return
     */
    private User d2e(UserDto userDto){
        User user = new User();
        if (userDto != null){
            BeanUtils.copyProperties(userDto, user);
            if (userDto.getPackagesId() != null){
                for(Integer i : userDto.getPackagesId()){
                    user.getPackageSet().add(packageDao.get(Package.class, i));
                }
            }
        }
        return user;
    }

    /**
     * 将user实体类对象转换为userDto对象
     * @param user
     * @return
     */
    private UserDto e2d(User user){
        UserDto userDto = new UserDto();
        if (user != null){
            BeanUtils.copyProperties(user, userDto);
            if (user.getPackageSet() != null && user.getPackageSet().size() != 0){
                for (Package p: user.getPackageSet()) {
                    userDto.getPackagesId().add(p.getId());
                    userDto.getPackagesName().add(p.getName());
                }
            }
        }
        return userDto;
    }

    /**
     * 将user数组转化为userDto数组
     * @param userList
     * @return
     */
    private List<UserDto> e2d(List<User> userList){
            List<UserDto> userDtos = new ArrayList<>();
            if (userList != null){
                for (User u:userList) {
                    userDtos.add(this.e2d(u));
                }
            }
            return userDtos;
    }


}
