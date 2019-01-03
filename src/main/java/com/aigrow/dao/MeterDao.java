package com.aigrow.dao;

import com.aigrow.model.entity.Meter;

public interface MeterDao extends BaseDao<Meter>{

    /**
     * 批量删除
     * @param meterIds
     * @return
     */
    void batchDelete(String meterIds);

    /**
     * 保存/修改meter对象信息
     * @param meter
     * @return
     */
    int merge(Meter meter);
}
