package com.develop.bank.model.card;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

public class CardAmountType {

    private String amountType;

    public CardAmountType() {

    }

    public CardAmountType(String amountType) {
        setAmountType(amountType);
    }

    public void setAmountType(String amountType) {
        this.amountType = amountType;
    }

    public String getAmountType() {
        return amountType;
    }
}
