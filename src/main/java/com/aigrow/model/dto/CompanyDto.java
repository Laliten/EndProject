package com.aigrow.model.dto;

import java.util.HashSet;
import java.util.Set;

/**
 * @author YangDeJian
 */
public class CompanyDto {
    private int id;
    private String name;
    private String code;
    private String custmoerServiceTel;
    private String trustDegree;
    private String place;
    private Set<Integer> wayBillIds = new HashSet<>();
    private Set<String> wayBillCodes = new HashSet<>();

    public Set<Integer> getWayBillIds() {
        return wayBillIds;
    }

    public void setWayBillIds(Set<Integer> wayBillIds) {
        this.wayBillIds = wayBillIds;
    }

    public Set<String> getWayBillCodes() {
        return wayBillCodes;
    }

    public void setWayBillCodes(Set<String> wayBillCodes) {
        this.wayBillCodes = wayBillCodes;
    }

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCustmoerServiceTel() {
        return custmoerServiceTel;
    }

    public void setCustmoerServiceTel(String custmoerServiceTel) {
        this.custmoerServiceTel = custmoerServiceTel;
    }

    public String getTrustDegree() {
        return trustDegree;
    }

    public void setTrustDegree(String trustDegree) {
        this.trustDegree = trustDegree;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

}
