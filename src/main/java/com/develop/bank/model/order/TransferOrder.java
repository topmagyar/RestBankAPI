package com.develop.bank.model.order;

import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

@Entity(name = "orders")
public class TransferOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardFrom;
    private String cardTo;
    private Long amount;

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

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getAmount() {
        return amount;
    }
}
