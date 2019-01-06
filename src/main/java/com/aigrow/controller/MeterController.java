package com.aigrow.controller;

import com.aigrow.model.dto.MeterDto;
import com.aigrow.model.dto.Json;
import com.aigrow.model.dto.Page;
import com.aigrow.model.dto.SessionInfo;
import com.aigrow.service.MeterService;
import com.aigrow.util.ConfigUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @RequestMapping(value = "/update",method = RequestMethod.POST)
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

    private static Logger logger = LoggerFactory.getLogger(MeterController.class);

    /**
     * 处理表中的删除按钮的请求，处理结束返回Json对象用于显示
     * @return
     */
    @RequestMapping("/singleDelete")
    @ResponseBody
    public Json singleDelete(MeterDto meterDto){
        Json json = new Json();
        logger.info("     "+meterDto.getId());
        meterService.singleDelete(meterDto);
        json.setSuccess(true);
        json.setMsg("删除成功！");
        return json;
    }

    /**
     * 处理确认批量删除的请求，处理结束返回Json对象用于显示
     * @return
     */
    @RequestMapping(value = "/batchDelete",method = RequestMethod.POST)
    @ResponseBody
    public Json batchDelete(String meterIds){
        Json json = new Json();
        logger.info("     "+meterIds);
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
    public Json getMeter(HttpSession session, Page page, String companyCode){
        Map<String, Object> map = new HashMap<>(0);

        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());


        long numOfMeters = meterService.numOfMeters(companyCode);
        long totalPages = numOfMeters%page.getPageSize()==0?
                (numOfMeters/page.getPageSize()):((numOfMeters/page.getPageSize())+1);
        page.setTotalRecordSize(numOfMeters);
        page.setTotalPages(totalPages);
        sessionInfo.setPage(page);
        session.setAttribute(ConfigUtil.getSessionInfoName(),sessionInfo);
        map.put("page", page);

        List<MeterDto> companyMeter = meterService.getCompanyMeter(companyCode, page);
        map.put("allMeters", companyMeter);

        Json json = new Json();
        try{
            if (companyMeter != null && companyMeter.size() != 0){
                json.setSuccess(true);
                json.setMsg("查询成功");
                json.setObj(map);
            } else {
                json.setSuccess(false);
                json.setMsg("当前还未有数据");
                json.setObj(map);
            }
        } catch (Exception e){
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        return json;
    }

}
