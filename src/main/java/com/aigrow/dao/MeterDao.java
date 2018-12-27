package com.aigrow.dao;

import com.aigrow.model.entity.Meter;

import java.util.List;
import java.util.Map;

public interface MeterDao extends BaseDao<Meter>{
    List findBySQL(String sql, Map<String,Object> map);
}
