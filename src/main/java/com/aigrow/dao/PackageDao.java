package com.aigrow.dao;

import com.aigrow.model.entity.Package;

import java.util.List;
import java.util.Map;

/**
 * 包裹数据库操作类
 * @author dingquanbo
 */
public interface PackageDao extends BaseDao<Package>{
    /**
     * 重写findBySQL方法，通过sql语句查询
     * @param sql
     * @param map
     * @return
     */
    List findBySQL(String sql, Map<String,Object> map);
}
