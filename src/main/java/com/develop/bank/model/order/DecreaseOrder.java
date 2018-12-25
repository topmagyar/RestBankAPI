package com.develop.bank.model.order;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

public class DecreaseOrder {

    private Long id;
    private String cardNumber;
    private Long amount;

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getAmount() {
        return amount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }
}
