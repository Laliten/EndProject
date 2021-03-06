package com.aigrow.dao.impl;

import com.aigrow.dao.HistoryDao;
import com.aigrow.model.entity.History;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author GaoJiaHui
 */
@Repository
public class HistoryDaoImpl extends BaseDaoImpl<History> implements HistoryDao {

    /**
     * 查找附近驿站历史纪录
     * @param userId
     * @return
     */
    @Override
    public List<History> findHistory(int userId,String type) {
        Query query= getCurrentSession().createQuery("select h from History h,User u where h.id=u.id and h.users.id='"+userId+"' and h.type='"+type+"'");
        List<History> list = query.list();
        return list;
    }

    /**
     * 添加查找附近驿站历史纪录
     * @param history
     */
    @Override
    public void merge(History history) {
        getCurrentSession().merge(history);
    }
}
