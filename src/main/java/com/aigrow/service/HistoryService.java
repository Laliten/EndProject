package com.aigrow.service;

import com.aigrow.model.dto.HistoryDto;

import java.util.List;

/**
 * @author GaoJiaHui
 */
public interface HistoryService {

    /**
     * 查找附近驿站历史纪录
     * @param userId,type
     * @return
     */
    List<HistoryDto> findHistory(int userId,String type);

    /**
     * 添加查找附近驿站历史纪录
     * @param historyDto
     */
    void addHistory(HistoryDto historyDto);
}
