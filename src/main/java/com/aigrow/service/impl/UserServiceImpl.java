package com.aigrow.service.impl;

import com.aigrow.dao.UserDao;
import com.aigrow.model.dto.Page;
import com.aigrow.model.dto.UserDto;
import com.aigrow.model.entity.Package;
import com.aigrow.model.entity.User;
import com.aigrow.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YangDeJian
 */
@Service
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
    public List<UserDto> getAllAdmins(Page page) {
        String hql = "from User u where u.type=1";
        List<User> userList = userDao.find(hql, page.getNextPage(), page.getPageSize());
        return this.e2d(userList);
    }

    /**
     * 进行登录验证，正确则返回一个dto对象，失败则返回null
     *
     * @param user
     * @return
     */
    @Override
    public UserDto login(User user) {
        UserDto userDto = this.e2d(user);
        /**
         * 代码写在这里
         */
        return userDto;
    }

    /**
     * 进行注册验证，验证用户名是否唯一，正确返回0，失败返回1
     *
     * @param user
     * @return
     */
    @Override
    public int register(User user) {
        String hql = "select count(*) from User u where u.account=?";
        long state = userDao.count(hql, user.getAccount());
        if (state == 1){
            return 1;
        } else {
            return 0;
        }
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
