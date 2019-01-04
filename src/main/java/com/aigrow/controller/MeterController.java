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
    @ResponseBody
    public Json add(MeterDto meterDto){
        Json json = new Json();
        int num = meterService.add(meterDto);
        if(num==1){
            json.setSuccess(true);
            json.setMsg("添加成功！");
        }
        else {
            json.setSuccess(false);
            json.setMsg("添加失败！");
        }
        return json;
    }

    /**
     * 处理计价表下的修改功能
     * @param meterDto
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public Json update(MeterDto meterDto){
        Json json = new Json();
        int num = meterService.update(meterDto);
        if(num == 1){
            json.setSuccess(true);
            json.setMsg("添加成功！");
        }
        else {
            json.setMsg("添加失败！");
        }
        return json;
    }

    /**
     * 处理表中的删除按钮的请求，处理结束返回Json对象用于显示
     * @return
     */
    @RequestMapping("/singleDelete")
    @ResponseBody
    public Json singleDelete(MeterDto meterDto){
        Json json = new Json();
        meterService.singleDelete(meterDto.getId());
        json.setSuccess(true);
        json.setMsg("删除成功！");
        return json;
    }

    /**
     * 处理确认批量删除的请求，处理结束返回Json对象用于显示
     * @return
     */
    @RequestMapping("/batchDelete")
    @ResponseBody
    public Json batchDelete(String meterIds){
        Json json = new Json();

        meterService.batchDelete(meterIds);
        json.setSuccess(true);
        json.setMsg("删除成功！");
        return json;
    }

    /**
     * 处理点击计价表按钮或其下的韵达按钮的请求
     * @return
     */
    @RequestMapping({"/index"})
    public String index(String companyName){

        return "admin/"+companyName.trim();
    }

    /**
     * 当对表的请求的url为
     * @return
     */
    @RequestMapping("/none")
    public String error(){
        return "error/error";
    }

    /**
     * 处理单击计价表按钮下的申通按钮的请求
     * @return
     */
    @ResponseBody
    @RequestMapping("/companyMeter")
    public Json getMeter(Page page, String companyCode){
        Json json = new Json();
        List<MeterDto> companyMeter = meterService.getCompanyMeter(companyCode, page);
        try{
            if (companyMeter != null && companyMeter.size() != 0){
                json.setSuccess(true);
                json.setMsg("查询成功");
                json.setObj(companyMeter);
            } else {
                json.setMsg("当前还未有数据");
            }
        } catch (Exception e){
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        return json;
    }

}
