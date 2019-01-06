package com.aigrow.controller;

import com.aigrow.model.dto.*;
import com.aigrow.service.MeterService;
import com.aigrow.service.UserService;
import com.aigrow.util.ConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 搜索控制器
 * @author YangDeJian
 */
@Controller
@RequestMapping("/searchController")
public class SearchController {

    @Autowired
    private UserService userService;
    @Autowired
    private MeterService meterService;

    /**
     * 处理搜索请求
     * @return
     */
    @ResponseBody
    @RequestMapping("/doSearch")
    public Json doSearch(HttpSession session, String searchText, String type, Page page){
        Map<String,Object> map = new HashMap<>(0);
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
        Json json = new Json();
        long numOfSearchRecords;
        //处理分页

        // user表示查询的类型是用户，meter表示查询的类型是计价表
        if ("user".equals(type)){
            List<UserDto> userDtos = userService.searchUser(page,searchText);
            map.put("allUsers",userDtos);
            numOfSearchRecords = userDtos.size();
        } else if ("meter".equals(type)){
            List<MeterDto> meterDtos = meterService.searchMeters(page, searchText);
            map.put("allMeters", meterDtos);
            numOfSearchRecords = meterDtos.size();
        } else {
            numOfSearchRecords = 0;
        }

        page.setTotalPages(1);
        page.setTotalRecordSize(numOfSearchRecords);
        page.setPageSize((int)numOfSearchRecords);
        sessionInfo.setPage(page);
        session.setAttribute(ConfigUtil.getSessionInfoName(), sessionInfo);
        map.put("page", page);

        json.setObj(map);
        if (map.size()>1){
            json.setSuccess(true);
            json.setMsg("查询成功");
        } else {
            json.setSuccess(false);
            json.setMsg("查询失败，造成的原因可能是指向不明");
        }
        return json;
    }
}
