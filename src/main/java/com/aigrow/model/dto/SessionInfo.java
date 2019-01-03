package com.aigrow.model.dto;

import com.aigrow.model.dto.UserDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 将往前端传送的数据保存在这里
 * @author YangDeJian
 */
public class SessionInfo implements Serializable {
    private String id;
    private String username;
    private List<UserDto> userDtos=new ArrayList<>();
    /**
     * 将登录人的对象放到这里
     */
    private UserDto doneUser = new UserDto();

    public UserDto getDoneUser() {
        return doneUser;
    }

    public void setDoneUser(UserDto doneUser) {
        this.doneUser = doneUser;
    }

    public List<UserDto> getUserDtos() {
        return userDtos;
    }

    public void setUserDtos(List<UserDto> userDtos) {
        this.userDtos = userDtos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
