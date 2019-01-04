package com.aigrow.service.impl;

import com.aigrow.controller.PostController;
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

import java.util.*;

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
        String hql = "select h from History h,User u where h.users.id=u.id and u.id=:userId and h.type=:type";
        Map<String,Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("type", type);
        List<History> history = historyDao.find(hql,params);
        List<HistoryDto> historyDto = e2d(history);
        return historyDto;
    }

    /**
     * 添加查找附近驿站历史纪录
     * @param historyDto
     */
    @Override
    public void addHistory(HistoryDto historyDto) {
        if(historyDto.getPlace().equals("")||historyDto.getPlace().equals("undefined")){
            return;
        }
        History history = this.d2e(historyDto);
        historyDao.merge(history);
    }

    @Override
    public void addMeterHistory(HistoryDto historyDto) {
        historyDto.setType("1");
        historyDto.setTime(new Date());
        History history = this.d2e(historyDto);
        historyDao.merge(history);
    }


    /**
     * 将historyDto转换程history
     * @param historyDto
     * @return
     */
    private History d2e(HistoryDto historyDto) {
        History history = new History();
        if (historyDto != null){
            BeanUtils.copyProperties(historyDto, history);
            if(historyDto.getUserId()!=0){
                history.setUsers(userDao.get(User.class,historyDto.getUserId()));
            }
        }
        return history;
    }

    /**
     * 将history转换程historyDto
     * @param history
     * @return
     */
    private HistoryDto e2d(History history){
        HistoryDto historyDto = new HistoryDto();
        if (history != null){
            BeanUtils.copyProperties(history,historyDto);
            if(history.getUsers()!=null){
                historyDto.setUserId(history.getUsers().getId());
                historyDto.setUserName(history.getUsers().getName());
            }
        }
        return historyDto;
    }

    /**
     * 将List<History>转换程ListList<HistoryDto>
     * @param historyList
     * @return
     */
    private List<HistoryDto> e2d(List<History> historyList){
        List<HistoryDto> list = new ArrayList<>();
        if (historyList != null && historyList.size() != 0){
            for (History h:historyList) {
                list.add(this.e2d(h));
            }
        }
        return list;
    }


}
