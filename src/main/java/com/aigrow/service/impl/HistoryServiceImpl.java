package com.aigrow.service.impl;

import com.aigrow.dao.HistoryDao;
import com.aigrow.dao.UserDao;
import com.aigrow.model.dto.HistoryDto;
import com.aigrow.model.entity.History;
import com.aigrow.model.entity.User;
import com.aigrow.service.HistoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GaoJiaHui
 */
@Service
@Transactional
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    private HistoryDao historyDao;
    @Autowired
    private UserDao userDao;

    /**
     * 查找附近驿站历史纪录
     * @param userId
     * @return
     */
    @Override
    public List<HistoryDto> findHistory(int userId,String type) {
        List<History> history = historyDao.findHistory(userId,type);
        List<HistoryDto> historyDto = e2d(history);
        return historyDto;
    }

    /**
     * 添加查找附近驿站历史纪录
     * @param historyDto
     */
    @Override
    public void addHistory(HistoryDto historyDto) {
        History history = this.d2e(historyDto);
        historyDao.save(history);
    }

    private History d2e(HistoryDto historyDto) {
        History history = new History();
        if (historyDto != null){
            BeanUtils.copyProperties(historyDto, history);
            if(historyDto.getUserId()!=0){
                history.setUser(userDao.get(User.class,historyDto.getUserId()));
            }
        }
        return history;
    }

    private HistoryDto e2d(History history){
        HistoryDto historyDto = new HistoryDto();
        if (history != null){
            BeanUtils.copyProperties(history,historyDto);
        }
        return historyDto;
    }

    private List<HistoryDto> e2d(List<History> userList){
        List<HistoryDto> list = new ArrayList<>();
        if (userList != null){
            for (History h:userList) {
                list.add(this.e2d(h));
            }
        }
        return list;
    }
}
