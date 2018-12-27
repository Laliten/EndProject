package com.aigrow.dao;

import com.aigrow.model.entity.Company;

import java.util.List;
import java.util.Map;

public interface CompanyDao extends BaseDao<Company>{
    List findBySQL(String sql, Map<String,Object> map);
}
