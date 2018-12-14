package com.aigrow.dao;

import com.aigrow.model.entity.User;

/**
 * 定义用户的操作
 * @author YangDeJian
 */
public interface UserDao {
    /**
     * 通过对user对象与后台数据的对比，判断是否存在该用户
     *  若不存在，则返回null
     * @param user
     * @return
     */
    User get(User user);

    /**
     * 通过查询后台用户名的数量进行用户名是否存在
     * @param user
     * @return
     */
    int count(User user);
}
