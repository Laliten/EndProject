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
    @Override
    public void batchDelete(String hql, Map<String, List<Integer>> map) {
        Query query = getCurrentSession().createQuery(hql);
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
                query.setParameterList(entry.getKey(), entry.getValue());
            }
        }

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
