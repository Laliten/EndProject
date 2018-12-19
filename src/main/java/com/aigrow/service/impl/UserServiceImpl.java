package com.aigrow.service.impl;

import com.aigrow.dao.UserDao;
import com.aigrow.model.dto.UserDto;
import com.aigrow.model.entity.User;
import com.aigrow.service.UserService;
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
        /**
         * 代码写在这里
         */
        return userDto;
    }

    /**
     * 将user数组转化为userDto数组
     * @param userList
     * @return
     */
    private List<UserDto> e2d(List<User> userList){
        List<UserDto> userDtos = new ArrayList<>();
        /**
         * 代码写在这里
         */
        return userDtos;
    }
}
