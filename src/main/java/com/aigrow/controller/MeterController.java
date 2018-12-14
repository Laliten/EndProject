package com.aigrow.controller;

import com.aigrow.model.dto.Json;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author YangDeJian
 */
@Controller
@RequestMapping("/meterController")
public class MeterController {
    /**
     * 处理计价表下的确认添加的请求，处理结束返回Json对象用于显示
     * @return
     */
    @RequestMapping("/add")
    public Json add(){

        return null;
    }

    /**
     * 处理表中的删除按钮的请求，处理结束返回Json对象用于显示
     * @return
     */
    @RequestMapping("/singleDelete")
    public Json singleDelete(){
        return null;
    }

    /**
     * 处理确认批量删除的请求，处理结束返回Json对象用于显示
     * @return
     */
    @RequestMapping("/batchDelete")
    public Json batchDelete(){
        return null;
    }

    /**
     * 处理点击计价表按钮或其下的韵达按钮的请求
     * @return
     */
    @RequestMapping({"/index","/yunda"})
    public String index(){

        return "admin/yunDa";
    }

    /**
     * 处理单击计价表按钮下的申通按钮的请求
     * @return
     */
    @RequestMapping("/shenTong")
    public String shenTong(){

        return "admin/shenTong";
    }

    /**
     * 处理单击天天按钮的请求
     * @return
     */
    @RequestMapping("/tianTian")
    public String tianTian(){
        return "/admin/tianTian";
    }

    /**
     * 有多少个计价表，就有多少个方法
     */
}
