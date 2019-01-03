package com.aigrow.dao.impl;

import com.aigrow.dao.MeterDao;
import com.aigrow.model.entity.Meter;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XQH
 */
@Repository
public class MeterDaoImpl extends BaseDaoImpl<Meter> implements MeterDao{

    /**
     * 批量删除
     * @param meterIds
     * @return
     */
    @Override
    public void batchDelete(String meterIds) {
        if(meterIds==null||meterIds.trim().length()==0){
            return;
        }
        List<Integer> idList = new ArrayList<Integer>(0);
        String[] idArr = meterIds.split(",");
        for(String id:idArr){
            if(id!=null){
                idList.add(Integer.parseInt(id));
            }
        }

        Query query = getCurrentSession().createQuery("delete from Meter m where m.id in:ids");
        query.setParameter("ids",idList);
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
