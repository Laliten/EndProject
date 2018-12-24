package com.aigrow.dao.impl;

import com.aigrow.dao.UserDao;
import com.aigrow.model.entity.User;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author YangDeJian
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
    /**
     * 判断用户名是否唯一
     *
     * @param hql
     * @param account
     * @return
     */
    @Override
    public Long count(String hql, String account) {
        Query q = this.getCurrentSession().createQuery(hql).setString(1,account);
        return (Long) q.uniqueResult();
    }



}
