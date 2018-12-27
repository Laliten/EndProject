package com.aigrow.dao.impl;

import com.aigrow.dao.PackageDao;
import com.aigrow.model.entity.Package;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dingquanbo
 */
@Repository
public class PackageDaoImpl extends BaseDaoImpl<Package> implements PackageDao{
    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    /**
     * 重写findBySQL方法，通过sql语句查询
     * @param sql
     * @param map
     * @return
     */
    public List findBySQL(String sql, Map<String, Object> map){
        SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
        sqlQuery.addEntity(Package.class);
        if (map!=null&&!map.isEmpty()){
            for (String key:map.keySet()){
                sqlQuery.setParameter(key,map.get(key));
            }
        }
        return sqlQuery.list();
    }
}
