package com.develop.bank.DAO;

import com.develop.bank.model.Card;

import java.util.List;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

public interface CardDAO {

    Card saveCard(Card card);
    String removeCard(String cardNumber);
    String updateCard(Card card);
    Card getCard(String cardNumber);
    List<Card> getCards();
}
