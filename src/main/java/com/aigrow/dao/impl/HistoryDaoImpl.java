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
     * 添加查找附近驿站历史纪录
     * @param history
     */
    @Override
    public void merge(History history) {
        getCurrentSession().merge(history);
    }
}
