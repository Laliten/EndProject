package com.aigrow.model.dto;

/**
 * @author dingquanbo
 * 查询结果
 *
 */
public class CostEstimateDto {
    private int id;
    private String name;
    private int cost;
    private String destination;
    private String start;
    private String trustDegree;
    private String distance;

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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getTrustDegree() {
        return trustDegree;
    }

    public void setTrustDegree(String trustDegree) {
        this.trustDegree = trustDegree;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
