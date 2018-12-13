package com.aigrow.model.dto;

import java.util.HashSet;
import java.util.Set;

/**
 * @author YangDeJian
 */
public class UserDto {
    private int id;
    private String name;
    private String account;
    private String password;
    private String iconUrl;
    private String type;
    private Set<Integer> packagesId = new HashSet<>();
    private Set<String> packagesName = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Integer> getPackagesId() {
        return packagesId;
    }

    public void setPackagesId(Set<Integer> packagesId) {
        this.packagesId = packagesId;
    }

    public Set<String> getPackagesName() {
        return packagesName;
    }

    public void setPackagesName(Set<String> packagesName) {
        this.packagesName = packagesName;
    }
}
