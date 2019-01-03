package com.aigrow.controller;

import com.aigrow.model.dto.HistoryDto;
import com.aigrow.model.dto.Json;
import com.aigrow.model.entity.History;
import com.aigrow.service.HistoryService;
import com.aigrow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author YangDeJian
 */
@Controller
@RequestMapping("/postController")
public class PostController {

    @Autowired
    private HistoryService historyService;
    /**
     * 处理单击附近驿站的请求，跳转界面
     * @return
     */
    @RequestMapping("/nearby")
    public String nearby(){
        return "user/nearby";
    }

    /**
     * 查找附近驿站历史纪录
     * @param historyDto
     * @return
     */
    @RequestMapping("/findhistory")
    public ModelAndView findHistory(HistoryDto historyDto){
        ModelAndView mv = new ModelAndView("user/history");
        historyDto.setType("1");
        List<HistoryDto> result = historyService.findHistory(historyDto.getUserId(),historyDto.getType());
        mv.addObject("history",result);
        return mv;
    }

    /**
     * 添加查找附近驿站历史纪录
     * @param historyDto
     */
    @RequestMapping("/addhistory")
    @ResponseBody
    public Json addHistory(HistoryDto historyDto){
        Date date = new Date();//获得系统时间.
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        String nowTime = sdf.format(date);
        try {
            date = sdf.parse( nowTime );
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Json json = new Json();
        historyDto.setType("1");
        historyDto.setTime(date);
        historyService.addHistory(historyDto);
        return json;
    }

}
