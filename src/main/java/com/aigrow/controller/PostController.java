package com.aigrow.controller;

import com.aigrow.model.dto.Json;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author YangDeJian
 */
@Controller
@RequestMapping("/postController")
public class PostController {
    /**
     * 处理单击附近驿站的请求，跳转界面
     * @return
     */
    @RequestMapping("/nearby")
    public String nearby(){
        return "user/nearby";
    }

    /**
     * 在地图上单击水滴，显示该地点的 请求
     * @return
     */
    @RequestMapping("/showInfo")
    public Json showPostInfo(){
        return null;
    }
}
