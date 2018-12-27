package com.aigrow.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author YangDeJian
 */
@Entity
@Table(name = "tcompany")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column(name = "customer_service_tel")
    private String custmoerServiceTel;
    @Column(name = "trust_degree")
    private String trustDegree;
    @Column
    private String place;
    @Column
    private String code;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Set<WayBill> wayBillSet = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "company")
    private Set<Meter> meterSet = new HashSet<>();

    public Set<Meter> getMeterSet() {
        return meterSet;
    }

    public void setMeterSet(Set<Meter> meterSet) {
        this.meterSet = meterSet;
    }

    public Company() {
    }

    public Set<WayBill> getWayBillSet() {
        return wayBillSet;
    }

    public void setWayBillSet(Set<WayBill> wayBillSet) {
        this.wayBillSet = wayBillSet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
