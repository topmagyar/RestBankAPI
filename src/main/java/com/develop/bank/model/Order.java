package com.develop.bank.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

@Entity(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardFrom;
    private String cardTo;
    private String orderType;
    private String amount;

    public void setId(Long id) {
        this.id = id;
    }

    public void setCardFrom(String cardFrom) {
        this.cardFrom = cardFrom;
    }

    public void setCardTo(String cardTo) {
        this.cardTo = cardTo;
    }

    public Long getId() {
        return id;
    }

    public String getCardFrom() {
        return cardFrom;
    }

    public String getCardTo() {
        return cardTo;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getAmount() {
        return amount;
    }

    public String getOrderType() {
        return orderType;
    }
}
