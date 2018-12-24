package com.aigrow.service;

import com.aigrow.model.dto.Page;
import com.aigrow.model.dto.UserDto;

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
     * @return
     */
    List<UserDto> getAllAdmins(Page page);

    /**
     * 进行注册验证，验证用户名是否唯一，正确返回0，失败返回1
     * @param account
     * @return
     */
    long checkUsername(String account);
}
