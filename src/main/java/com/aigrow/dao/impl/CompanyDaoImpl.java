package com.aigrow.dao.impl;

import com.aigrow.dao.BaseDao;
import com.aigrow.dao.CompanyDao;
import com.aigrow.model.entity.Company;
import com.aigrow.model.entity.Meter;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CompanyDaoImpl extends BaseDaoImpl<Company> implements CompanyDao{
    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    /**
     * 重写findBySQL，用SQL查询
     * @param sql
     * @param map
     * @return
     */
    @Override
    public List findBySQL(String sql, Map<String, Object> map) {
        SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
        sqlQuery.addEntity(Company.class);
        if (map!=null&&!map.isEmpty()) {
            sqlQuery.setParameter(0, map.get("id"));
        }
        return sqlQuery.list();
    }
}
