package com.aigrow.model.entity;

import javax.persistence.*;

/**
 * @author YangDeJian
 */
@Entity
@Table(name = "tmeter")
public class Meter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String destination;
    @Column(name = "first_weight_price")
    private Integer firstWeightPrice;
    @Column(name = "next_weight_price")
    private Integer nextWeightPrice;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_code")
    private Company company;

    public Meter() {
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Integer getFirstWeightPrice() {
        return firstWeightPrice;
    }

    public void setFirstWeightPrice(Integer firstWeightPrice) {
        this.firstWeightPrice = firstWeightPrice;
    }

    public Integer getNextWeightPrice() {
        return nextWeightPrice;
    }

    public void setNextWeightPrice(Integer nextWeightPrice) {
        this.nextWeightPrice = nextWeightPrice;
    }
}
