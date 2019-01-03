package com.aigrow.controller;

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

}
