package com.aigrow.controller;

import com.aigrow.model.dto.SessionInfo;
import com.aigrow.model.dto.UserDto;
import com.aigrow.service.UserService;
import com.aigrow.util.ConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录注册控制器
 * @author YangDeJian
 */
@Controller
@RequestMapping("/appController")
public class AppController {

    @Autowired
    private UserService userService;
    /**
     *  通过url访问，跳转至登录界面
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "user/login";
    }

    /**
     * 登出系统
     * @return
     */
    @RequestMapping("/loginOut")
    public String loginOut(){
        return "user/login";
    }

    /**
     * 在登录界面点击注册，跳转至注册界面
     * @return
     */
    @RequestMapping("/register")
    public String register(){
        return "user/register";
    }

    /**
     * 出现任何错误，跳转至error界面
     * @return
     */
    @RequestMapping("/error")
    public String error(){
        return "error/error";
    }

    /**
     * 在用户主界面中，请求介绍界面
     * @return
     */
    @RequestMapping("/userHome_intro")
    public String userHomeIntro(){
        return "user/userHome_intro";
    }

    /**
     * 跳转至用户主界面
     * @return
     */
    @RequestMapping("/userHome")
    public String userHome(){
        return "user/userHome";
    }

    /**
     * 跳转至运费估计界面
     * @return
     */
    @RequestMapping("/costEstimate")
    public String costEstimate(){
        return "user/costEstimate";
    }

    /**
     * 跳转至附近驿站界面
     * @return
     */
    @RequestMapping("/nearby")
    public String nearby(){
        return "user/nearby";
    }

    /**
     * 跳转至运单查询界面
     * @return
     */
    @RequestMapping("/wayBillQuery")
    public String wayBillQuery(){
        return "user/wayBillQuery";
    }

    /**
     * 在用户主界面中，请求图片轮播界面
     * @return
     */
    @RequestMapping("/userHome_loopPlay")
    public String userHomeLoopPlay(){
        return "user/userHome_loopPlay";
    }

    /**
     * 加载管理用户（管理员）的界面
     * @return
     */
    @RequestMapping("/manager_manager")
    public String manager(){
        return "admin/manager_manager";
    }


    /**
     * 处理登录,返回userHome或者adminHome
     * @return
     */
    @RequestMapping("/doLogin")
    public ModelAndView doLogin(HttpSession session, UserDto userDto, String type){
        ModelAndView mv = new ModelAndView();
        UserDto doneUser  = userService.login(userDto);

        SessionInfo sessionInfo = new SessionInfo();
        sessionInfo.setDoneUser(doneUser);



        if(doneUser != null){
            if ( "0".equals(type)){
                //0代表用户
                mv.setViewName("user/userHome");
            } else if ("1".equals(type)){
                //1代表管理员
                mv.setViewName("admin/manager");
            }
        } else {
            mv.setViewName("user/login");
        }
        mv.addObject(ConfigUtil.getSessionInfoName(),sessionInfo);
        session.setAttribute(ConfigUtil.getSessionInfoName(),sessionInfo);
        return mv;
    }

    /**
     * 点击注册提交后，该方法处理，返回userHome
     * @param userDto
     * @return
     */
    @RequestMapping("/doRegister")
    public ModelAndView doRegister(UserDto userDto){
        ModelAndView mv = new ModelAndView();
        int num = userService.register(userDto);

        if(num == 1){
            mv.setViewName("user/login");
        }else {
            mv.setViewName("user/register");
        }
        return mv;
    }

    /**
     * 注册验证用户名的唯一性。
     * @param userDto
     * @return resultMap
     */
    @ResponseBody
    @RequestMapping(value = "/checkUsername",method = RequestMethod.POST)
    public Map<String,Object> check(UserDto userDto)
    {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        long num = userService.checkUsername(userDto.getAccount());

        if (num == 1){
            resultMap.put("type","true");
        } else {
            resultMap.put("type","false");
        }
        return resultMap;
    }

}
