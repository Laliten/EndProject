package com.aigrow.model.dto;

import com.aigrow.model.entity.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SessionInfo implements Serializable {
    private String id;
    private String username;
    private List<UserDto> userDtos=new ArrayList<>();

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
