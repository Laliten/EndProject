package com.aigrow.dao.impl;

import com.aigrow.dao.MeterDao;
import com.aigrow.model.entity.Meter;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class MeterDaoImpl extends BaseDaoImpl<Meter> implements MeterDao{

    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }
    @Override
    public List<Meter> findBySQL(String sql, Map<String, Object> map) {
        SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
        sqlQuery.addEntity(Meter.class);
        if (map!=null&&map.isEmpty()){
            for (String key:map.keySet()){
                sqlQuery.setParameter(key,map.get(key));
            }
        }
        return sqlQuery.list();
    }
}
