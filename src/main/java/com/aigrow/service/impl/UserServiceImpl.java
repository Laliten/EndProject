package com.aigrow.service.impl;

import com.aigrow.dao.UserDao;
import com.aigrow.model.dto.Page;
import com.aigrow.model.dto.SessionInfo;
import com.aigrow.model.dto.UserDto;
import com.aigrow.model.entity.Package;
import com.aigrow.model.entity.User;
import com.aigrow.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author YangDeJian
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    /**
     * 仅限管理员使用，获取所有的管理员
     *
     * @param page
     * @return
     */
    @Override
    public List<UserDto> getAllUsers(Page page, String type) {
        String hql = "from User u where u.type="+type;
        List<User> userList = userDao.find(hql, page.getNextPage(), page.getPageSize());
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
    public int register(UserDto userDto) {
        User user = new User();
        user.setType("0");
        Serializable num = userDao.save(this.d2e(userDto));
        if(num == null){
            return 0;
        }else {
            return 1;
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
     * 将user实体类对象转换为userDto对象
     * @param userDto
     * @return
     */
    private User d2e(UserDto userDto){
        User user = new User();
        if (userDto != null){
            BeanUtils.copyProperties(userDto, user);
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

    /**
     * xuqihao
     * @param id
     */
    @Override
    public void delete(String id){
        userDao.delete(userDao.get(User.class,id));
    }

    @Override
    public boolean editUserPwd(SessionInfo sessionInfo, String oldPwd, String newPwd){
        User u=userDao.get(User.class,sessionInfo.getId());
        if (u.getPassword().equalsIgnoreCase(oldPwd)){
            u.setPassword(newPwd);
            return true;
        }
        return false ;
    }

    @Override
    public void add(User user) throws Exception{

    }



}
