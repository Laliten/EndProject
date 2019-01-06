package com.aigrow.dao;

import com.aigrow.model.entity.Meter;

import java.util.List;
import java.util.Map;

public interface MeterDao extends BaseDao<Meter>{

    /**
     * 批量删除
     * @param
     * @return
     */
    void batchDelete(String hql, Map<String, List<Integer>> map);

    /**
     * 保存/修改meter对象信息
     * @param meter
     * @return
     */
    int merge(Meter meter);
}
