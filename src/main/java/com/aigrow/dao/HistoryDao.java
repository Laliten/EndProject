package com.aigrow.dao;

import com.aigrow.model.entity.History;

import java.util.List;

/**
 * @author GaoJiaHui
 */
public interface HistoryDao extends BaseDao<History> {

    /**
     * 添加查找附近驿站历史纪录
     * @param history
     */
    void merge(History history);
}
