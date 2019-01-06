package com.aigrow.dao.impl;

import com.aigrow.controller.MeterController;
import com.aigrow.dao.MeterDao;
import com.aigrow.model.entity.Meter;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author XQH
 */
@Repository
public class MeterDaoImpl extends BaseDaoImpl<Meter> implements MeterDao{
    /**
     * 批量删除
     * @param
     * @return
     */
    public void batchDelete(String hql, Map<String, List<Meter>> map) {
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("ids",map);
        query.executeUpdate();
    }


    /**
     * 保存/修改meter对象信息
     *
     * @param meter
     * @return
     */
    @Override
    public int merge(Meter meter) {
        getCurrentSession().merge(meter);
        return 0;
    }
}
