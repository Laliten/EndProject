package com.aigrow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录注册控制器
 * @author YangDeJian
 */
@Controller
@RequestMapping("/appController")
public class AppController {

    /**
     *  通过url访问，跳转至登录界面
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "login";
    }

    /**
     * 在登录界面点击注册，跳转至注册界面
     * @return
     */
    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    /**
     * 出现任何错误，跳转至error界面
     * @return
     */
    @RequestMapping("/error")
    public String error(){
        return "error";
    }

    /**
     * 处理登录,返回userHome或者adminHome
     * @return
     */
    @RequestMapping("/doLogin")
    public String doLogin(){

        return null;
    }

    /**
     * 点击注册提交后，该方法处理，返回userHome
     * @return
     */
    @RequestMapping("/doRegister")
    public String doRegister(){
        return "userHome";
    }

}
