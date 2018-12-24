package com.aigrow.dao;

import com.aigrow.model.entity.User;

/**
 * 用户数据库操作类
 * @author YangDeJian
 */
public interface UserDao extends BaseDao<User>{
    /**
     * 判断用户名是否唯一
     * @param hql
     * @param account
     * @return
     */
    public Long count(String hql, String account);

}
