package com.aigrow.controller;


import com.aigrow.model.dto.*;
import com.aigrow.model.dto.CostEstimateDto;
import com.aigrow.model.dto.Json;
import com.aigrow.model.dto.Page;
import com.aigrow.service.MeterService;
import com.aigrow.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 包裹控制器
 * @author YangDeJian
 */
@Controller
@RequestMapping("/packageController")
public class PackageController {

    @Autowired
    private MeterService meterService;

    @Autowired
    private PackageService packageService;

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


    @RequestMapping(value = "/costQuery",method = RequestMethod.POST)
    public @ResponseBody List costQuery(HttpSession session, @RequestParam("weight")Integer weight, @RequestParam("destination")String destination, @RequestParam("start")String start){
        Page page = new Page();
        Map<String,Object> map = new HashMap<>();
        List<CostEstimateDto> costEstimateDtoList = new ArrayList<>();
        costEstimateDtoList = meterService.cost(weight,destination,page);
        for (int i=0;i<costEstimateDtoList.size();i++){
            costEstimateDtoList.get(i).setStart(start);
        }
        map.put("data",costEstimateDtoList);
        map.put("total",costEstimateDtoList.size());
        return costEstimateDtoList;
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

    /**
     * 用户历史运单记录
     * @param id
     * @return
     */
    @RequestMapping("/history")
    public ModelAndView history(int id){
        ModelAndView modelAndView = new ModelAndView("user/history");
        List<PackageDto> packageDtoList = packageService.getHistory(id);
        modelAndView.addObject("packageDtoList",packageDtoList);
        return modelAndView;
    }
}
