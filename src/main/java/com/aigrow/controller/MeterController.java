package com.aigrow.controller;

import com.aigrow.model.dto.MeterDto;
import com.aigrow.model.dto.Json;
import com.aigrow.model.dto.Page;
import com.aigrow.service.MeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author YangDeJian
 */
@Controller
@RequestMapping("/meterController")
public class MeterController {
    @Autowired
    private MeterService meterService;

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
    @ResponseBody
    @RequestMapping("/companyMeter")
    public Json getMeter(Page page, String companyName){
        Json json = new Json();
        List<MeterDto> companyMeter = meterService.getCompanyMeter(companyName, page);
        try{
            if (companyMeter != null){
                json.setSuccess(true);
                json.setMsg("查询成功");
                json.setObj(companyMeter);
            }
        } catch (Exception e){
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        return json;
    }

}
