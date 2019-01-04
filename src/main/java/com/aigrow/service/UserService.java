package com.aigrow.service;

import com.aigrow.model.dto.Page;
import com.aigrow.model.dto.SessionInfo;
import com.aigrow.model.dto.UserDto;
import com.aigrow.model.entity.User;

import java.util.List;

/**
 * @author YangDeJian
 */
public interface UserService {
    /**
     * 进行登录验证，正确则返回一个dto对象，失败则返回null
     * @param userDto
     * @return
     */
    UserDto login(UserDto userDto);

    /**
     * 进行注册验证，验证用户名是否唯一，正确返回0，失败返回1
     * @param user
     * @return
     */
    int register(UserDto user);

    /**
     * 仅限管理员使用，获取所有的管理员
     * @param page
     * @param type
     * @return
     */
    List<UserDto> getAllUsers(Page page, String type);

    /**
     * 进行注册验证，验证用户名是否唯一，正确返回0，失败返回1
     * @param account
     * @return
     */
    long checkUsername(String account);

    /**
     * 进行批量删除
     * @param userIds
     */
    void batchDelete(String userIds);

    /**
     * 添加用户
     * @param userDto
     * @return
     */
    int add(UserDto userDto);

    /**
     * 删除用户
     * @param userId
     */
    void singleDelete(int userId);

    /**
     * 更新用户信息
     * @param userDto
     * @return
     */
    int update(UserDto userDto);

    /**
     * 修改当前用户的密码
     * @param userId
     * @param oldPwd
     * @param pwd
     * @return
     */
    boolean editCurrentUserPwd(String userId, String oldPwd, String pwd);
}
