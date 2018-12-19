package com.aigrow.controller;

import com.aigrow.model.dto.Json;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author YangDeJian
 */
@Controller
@RequestMapping("/personController")
public class PersonController {
    /**
     * 处理点击用户管理下管理员的请求，返回管理员的Json对象
     * @return
     */
    @RequestMapping("/adminManager")
    public Json doAdminManager(HttpSession session){


        return null;
    }

    /**
     * 处理单击用户管理和单击用户的请求，返回用户的Json对象
     * @return
     */
    @RequestMapping("/userManager")
    public Json doUserManager(){
        return null;
    }

    /**
     * 处理用户的批量删除, 重新获取所有用户
     * @return
     */
    @RequestMapping("/batchDelete")
    public Json batchDelete(){
        return null;
    }

    /**
     * 处理用户的添加，重新获取所有用户
     * @return
     */
    @RequestMapping("/add")
    public Json add(){
        return null;
    }

    /**
     * 处理用户的单个删除
     * @return
     */
    @RequestMapping("/singleDelete")
    public Json singleDelete(){
        return null;
    }

    /**
     * 处理用户的密码修改，成功后重新登录
     * @return
     */
    @RequestMapping("/modifyPassword")
    public String modifyPassword(){
        return "user/login";
    }

    /**
     * 处理用户的信息修改，重新获取所有用户
     * @param viewName
     * @return
     */
    @RequestMapping("/modifyUserInfo")
    public String modifyUserInfo(String viewName){
        return viewName;
    }

    /**
     * 处理单个用户的删除
     * @param viewName
     * @return
     */
    @RequestMapping("/singleDelete")
    public String delete(String viewName){
        return viewName;
    }
}
