package com.aigrow.controller;

import com.aigrow.model.dto.Json;
import com.aigrow.model.dto.Page;
import com.aigrow.model.dto.SessionInfo;
import com.aigrow.model.dto.UserDto;
import com.aigrow.model.entity.User;
import com.aigrow.service.UserService;
import com.aigrow.util.ConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            j.setMsg("当前还未有管理");
        }
        return j;
    }

    /**
     * 处理用户的批量删除, 重新获取所有用户
     * @return
     */
    @RequestMapping("/batchDelete")
    public Json batchDelete(String ids,HttpSession session){
        Json j=new Json();
        if(ids!=null&&ids.length()>0){
            for (String id:ids.split(",")){
                if (id!=null){
                    this.singleDelete(id,session);
                }
                j.setSuccess(true);
                j.setMsg("用户删除成功！");
            }
        }
        return j;
    }
    /**
     * 处理用户的单个删除
     * @return
     */
    @RequestMapping("/singleDelete")
    public Json singleDelete(String id,HttpSession session){
        SessionInfo sessionInfo= (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
        Json j=new Json();
        if(id!=null&&!id.equalsIgnoreCase(sessionInfo.getId())){
            userService.delete(id);
        }
        j.setSuccess(true);
        j.setMsg("删除成功！");
        return j;
    }
    /**
     * 处理用户的添加，重新获取所有用户
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Json add(User user){
        Json j=new Json();
        try {
            userService.add(user);
            j.setSuccess(true);
            j.setMsg("添加用户成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return j;
    }
    /**
     * 跳转到编辑用户密码页面
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("/editPwdPage")
    public String editPwdPage(Integer id, HttpServletRequest request){
//        User u=userService.get(id);
//        request.setAttribute("user",u);
        return "/personController/modifyPassword";
    }


    /**
     * 处理用户的密码修改，成功后重新登录
     * @return
     */
    @RequestMapping("/modifyPassword")
    @ResponseBody
    public Json modifyPassword(HttpSession session, String oldPwd, String newPwd){
        Json j=new Json();
        if (session!=null){
            SessionInfo sessionInfo= (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
            if (sessionInfo!=null){
                userService.editUserPwd(sessionInfo,oldPwd,newPwd);
                j.setSuccess(true);
                j.setMsg("修改密码成功,下次登录生效！");
            }else {
                j.setMsg("原密码错误！");
            }
        }
        return j;
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
     * 点击管理员按钮，跳转到管理员界面
     *
     */
    @ResponseBody
    @RequestMapping("/manage_manager")
    public List manage_manager(){
        return null;
    }

    /**
     * 点击用户按钮，跳转到用户界面
     */
    @ResponseBody
    @RequestMapping("/user")
    public List user(){
        return null;
    }
}
