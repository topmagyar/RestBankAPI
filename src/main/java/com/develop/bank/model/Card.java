package com.develop.bank.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

@Entity(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String amount;
    private String amountType;
    @NaturalId
    private String cardNumber;
    private String cardKey;

    public void setId(Long id) {
        this.id = id;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setAmountType(String amountType) {
        this.amountType = amountType;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCardKey(String cardKey) {
        this.cardKey = cardKey;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAmount() {
        return amount;
    }

    public String getAmountType() {
        return amountType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardKey() {
        return cardKey;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }
}
