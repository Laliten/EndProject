package com.aigrow.dao;

import com.aigrow.model.entity.Meter;

public interface MeterDao extends BaseDao<Meter>{

    /**
     * 批量删除
     * @param meterIds
     * @return
     */
    void batchDelete(String meterIds);

}
