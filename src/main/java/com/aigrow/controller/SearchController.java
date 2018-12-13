package com.aigrow.controller;

import com.aigrow.model.dto.Json;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 搜索控制器
 * @author YangDeJian
 */
@Controller
@RequestMapping("/searchController")
public class SearchController {
    /**
     * 处理搜索请求
     * @return
     */
    @RequestMapping("/doSearch")
    public Json doSearch(String searchInfo){
        return null;
    }
}
