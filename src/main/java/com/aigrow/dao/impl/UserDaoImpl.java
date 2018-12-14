package com.aigrow.dao.impl;

import com.aigrow.dao.UserDao;
import com.aigrow.model.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author YangDeJian
 */
@Repository
public class UserDaoImpl implements UserDao {
    /**
     * 通过对user对象与后台数据的对比，判断是否存在该用户
     * 若不存在，则返回null
     *
     * @param user
     * @return
     */
    @Override
    public User get(User user) {
        return null;
    }

    /**
     * 通过查询后台用户名的数量进行用户名是否存在
     *
     * @param user
     * @return
     */
    @Override
    public int count(User user) {
        return 0;
    }
}
