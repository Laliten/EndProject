package com.aigrow.model.dto;

/**
 * @author YangDeJian
 */
public class MeterDto {
    private int id;
    private String destination;
    private int firstWeightPrice;
    private int nextWeightPrice;
    private String companyName;
    private String companyCode;
    private int firstWeight;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getFirstWeightPrice() {
        return firstWeightPrice;
    }

    public void setFirstWeightPrice(int firstWeightPrice) {
        this.firstWeightPrice = firstWeightPrice;
    }

    public int getNextWeightPrice() {
        return nextWeightPrice;
    }

    public void setNextWeightPrice(int nextWeightPrice) {
        this.nextWeightPrice = nextWeightPrice;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public int getFirstWeight() {
        return firstWeight;
    }

    public void setFirstWeight(int firstWeight) {
        this.firstWeight = firstWeight;
    }
}
