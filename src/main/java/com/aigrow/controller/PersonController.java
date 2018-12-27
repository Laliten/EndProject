package com.aigrow.controller;

import com.aigrow.model.dto.Json;
import com.aigrow.model.dto.Page;
import com.aigrow.model.dto.UserDto;
import com.aigrow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author YangDeJian
 */
@Controller
@RequestMapping("/personController")
public class PersonController {
    @Autowired
    private UserService userService;
    /**
     * page对象的初始值
     *    pageSize = 10，currentPage = 1，
     *    totalPages = 1，nextPage = 2
     * 处理点击用户管理下管理员的请求，返回管理员的Json对象
     * @return
     */
    @ResponseBody
    @RequestMapping("/adminManager")
    public Json doAdminManager(@RequestParam Page page){
        List<UserDto> userDtos = userService.getAllUsers(page,"1");
        Json j = new Json();
        if (userDtos.size() != 0){
            j.setSuccess(true);
            j.setMsg("查询成功");
            j.setObj(userDtos);
        } else {
            j.setSuccess(false);
            j.setMsg("当前还未有管理员");
        }
        return j;
    }

    /**
     * 处理单击用户管理和单击用户的请求，返回用户的Json对象
     * @return
     */
    @ResponseBody
    @RequestMapping("/userManager")
    public Json doUserManager(@RequestParam Page page){
        List<UserDto> userDtos = userService.getAllUsers(page,"0");
        Json j = new Json();
        if (userDtos.size() != 0){
            j.setSuccess(true);
            j.setMsg("查询成功");
            j.setObj(userDtos);
        } else {
            j.setSuccess(false);
            j.setMsg("当前还未有用户");
        }
        return j;
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


}
