package com.aigrow.controller;

import com.aigrow.model.dto.Json;
import com.aigrow.model.dto.Page;
import com.aigrow.model.dto.SessionInfo;
import com.aigrow.model.dto.UserDto;
import com.aigrow.model.entity.User;
import com.aigrow.service.UserService;
import com.aigrow.util.ConfigUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public Json doAdminManager(HttpSession session, Page page){
        Map<String, Object> map = new HashMap<>(0);


        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
        // 1代表管理员，0代表用户
        long numOfUsers = userService.numOfUsers("1");
        List<UserDto> userDtos = userService.getAllUsers(page,"1");
        map.put("allUsers",userDtos);


        long totalPages = numOfUsers%page.getPageSize()==0?
                (numOfUsers/page.getPageSize()):((numOfUsers/page.getPageSize())+1);
        page.setTotalPages(totalPages);
        page.setTotalRecordSize(numOfUsers);
        sessionInfo.setPage(page);
        session.setAttribute(ConfigUtil.getSessionInfoName(), sessionInfo);
        map.put("page", page);

        Json j = new Json();
        if (userDtos.size() != 0){
            j.setSuccess(true);
            j.setMsg("查询成功");
            j.setObj(map);
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
    public Json doUserManager(HttpSession session, Page page){
        Map<String, Object> map = new HashMap<>(0);


        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
        // 1代表管理员，0代表用户
        long numOfUsers = userService.numOfUsers("0");
        List<UserDto> userDtos = userService.getAllUsers(page,"0");
        map.put("allUsers",userDtos);


        long totalPages = numOfUsers%page.getPageSize()==0?
                (numOfUsers/page.getPageSize()):((numOfUsers/page.getPageSize())+1);
        page.setTotalPages(totalPages);
        page.setTotalRecordSize(numOfUsers);
        sessionInfo.setPage(page);
        session.setAttribute(ConfigUtil.getSessionInfoName(), sessionInfo);
        map.put("page", page);

        Json j = new Json();
        if (userDtos.size() != 0){
            j.setSuccess(true);
            j.setMsg("查询成功");
            j.setObj(map);
        } else {
            j.setSuccess(false);
            j.setMsg("当前还未有用户");
            j.setObj(map);
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
            userService.singleDelete(Integer.parseInt(id));
        }
        j.setSuccess(true);
        j.setMsg("删除成功！");
        return j;
    }
    /**
     * 处理用户的添加，重新获取所有用户
     * @return
     */
    @ResponseBody
    @RequestMapping("/add")
    public Json add(HttpSession session, UserDto userDto){
        Json json = new Json();
        int num = userService.add(userDto);
        if (num == 1){
            json.setSuccess(true);
            json.setMsg("添加成功");
        } else {
            json.setSuccess(false);
            json.setMsg("添加失败");
        }
        return json;
    }

    /**
     * 处理用户的密码修改，成功后重新登录
     * @return
     */
    @ResponseBody
    @RequestMapping("/modifyPassword")
    public Json editCurrentUserPwd(HttpSession session, String oldPwd, String newPwd) {
        Json json = new Json();
        if (session != null) {
            SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
            if (sessionInfo != null) {
                if (userService.editCurrentUserPwd(sessionInfo.getDoneUser().getId(), oldPwd, newPwd)) {
                    json.setSuccess(true);
                    json.setMsg("修改密码成功，下次登录生效！");
                } else {
                    json.setMsg("原密码错误！");
                }
            } else {
                json.setMsg("登录超时，请重新登录！");
            }
        } else {
            json.setMsg("登录超时，请重新登录！");
        }
        return json;
    }

    @ResponseBody
    @RequestMapping("/editUserInfoAtAdminPost")
    public Json editUserInfoAtAdminPost(UserDto userDto){
        Json json = new Json();
        if (userDto.getId() != 0){
            UserDto doneUser = userService.update(userDto);
            json.setObj(doneUser);
            json.setSuccess(true);
            json.setMsg("修改信息成功");
        } else {
            json.setMsg("修改信息失败");
        }
        return json;
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
     * 修改用户信息
     * @return
     */
    @RequestMapping("/updateUserInfo")
    public ModelAndView updateUserInfoAtUserPost(UserDto userDto,HttpSession session,String pageName){
        ModelAndView mv = new ModelAndView();

        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());

        switch (pageName){
            case "nearby":
                mv.setViewName("user/nearby");
                break;
            case "costEstimate":
                mv.setViewName("user/costEstimate");
                break;
            case "userHome":
                mv.setViewName("user/userHome");
                break;
            case "wayBillQuery":
                mv.setViewName("user/wayBillQuery");
                break;
            case "userInfo":
                mv.setViewName("user/userInfo");
                break;
                default:break;
        }

        UserDto doneUser = userService.update(userDto);

        sessionInfo.setDoneUser(doneUser);
        mv.addObject(ConfigUtil.getSessionInfoName(),sessionInfo);
        session.setAttribute(ConfigUtil.getSessionInfoName(),sessionInfo);

        return mv;
    }

}
