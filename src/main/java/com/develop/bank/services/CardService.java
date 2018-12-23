package com.develop.bank.services;

import com.develop.bank.model.Card;

import java.util.List;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

public interface CardService {

    Card addCard(String token, String amountType);
    List<Card> getCards(String token);
    Card getCard(String token, String number);
    String removeCard(String token, String number);
}
