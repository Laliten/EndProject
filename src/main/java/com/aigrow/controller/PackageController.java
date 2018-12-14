package com.aigrow.controller;


import com.aigrow.model.dto.Json;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 包裹控制器
 * @author YangDeJian
 */
@Controller
@RequestMapping("/packageController")
public class PackageController {
    /**
     * 处理单击运单费用估计的请求，跳转界面
     * @return
     */
    @RequestMapping("/packageCost")
    public String packageCost(){
        return "user/costEstimate";
    }

    /**
     * 处理在运单费用估计界面的 提交 请求
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/costQuery")
    public Json costQuery(HttpSession session){
        return null;
    }

    /**
     * 处理运费估计的查看历史记录的请求
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/costHistory")
    public Json costHistory(HttpSession session){
        return null;
    }

    /**
     * 处理单击运单查询的请求，跳转界面
     * @return
     */
    @RequestMapping("/wayBillInfo")
    public String wayBillInfo(){
        return "user/wayBillInfo";
    }

    /**
     * 处理在运单查询界面，单击查询请求
     * @return
     */
    @ResponseBody
    @RequestMapping("/packageQuery")
    public Json packageQuery(){
        return null;
    }
}
