package com.develop.bank.services.impl;

import com.develop.bank.DAO.CardDAO;
import com.develop.bank.DAO.ConnectionDAO;
import com.develop.bank.DAO.TokenDAO;
import com.develop.bank.DAO.UserDAO;
import com.develop.bank.model.Card;
import com.develop.bank.model.ConnectionInfo;
import com.develop.bank.model.User;
import com.develop.bank.model.ValidToken;
import com.develop.bank.services.CardService;
import com.develop.bank.util.CryptTool;
import com.develop.bank.util.PasswordCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

@Service
@Transactional(readOnly = false)
public class CardServiceImpl implements CardService {

    @Autowired
    private CardDAO cardDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private TokenDAO tokenDAO;

    @Autowired
    private ConnectionDAO connectionDAO;

    @Override
    public Card addCard(String token, String username, String amountType) {
        ConnectionInfo connectionInfo = connectionDAO.getConnectionInfo(username);
        if (connectionInfo != null) {
            token = new CryptTool().decryptMessageByKey(token, connectionInfo.getInfo());
            User user = getUserByToken(token);
            if (checkTokenValid(token) && user != null) {
                Card card = new Card();
                card.setAmount(String.valueOf(0));
                card.setAmountType(amountType);
                card.setUserId(String.valueOf(user.getId()));
                card.setCardNumber(generateRandomCardNumber());
                String cardKey = "";
                for (int i = 0; i < 3; i++) {
                    cardKey += String.valueOf(new Random().nextInt(10));
                }
                PasswordCrypt passwordCrypt = new PasswordCrypt();
                String key = passwordCrypt.notZeroDeterm(username);
                String cryptedCardKey = new PasswordCrypt().encryptMessage(cardKey, key);
                card.setCardKey(cryptedCardKey);
                cardDAO.saveCard(card);
                return card;
            }
        }
        return null;
    }

    @Override
    public List<Card> getCards(String token) {
        if (checkTokenValid(token)) {
            return cardDAO.getCards();
        }
        return null;
    }

    @Override
    public Card getCard(String token, String number) {
        if (checkTokenValid(token)) {
            return cardDAO.getCard(number);
        }
        return null;
    }

    @Override
    public String removeCard(String token, String number) {
        if (checkTokenValid(token)) {
            cardDAO.removeCard(number);
            return "Success";
        }
        return "Error";
    }

    boolean checkTokenValid(String token) {
        ValidToken validToken = tokenDAO.get(token);
        return validToken != null;
    }

    User getUserByUsername(String username) {
        User user = userDAO.getUser("username", username);
        return user;
    }

    User getUserByToken(String token) {
        User user = userDAO.getUser("token", token);
        return user;
    }

    private String generateRandomCardNumber() {
        StringBuilder newCardNumber = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            newCardNumber.append(String.valueOf(new Random().nextInt(10)));
        }
        Card card = cardDAO.getCard(newCardNumber.toString());
        if (card == null) {
            return newCardNumber.toString();
        } else {
            return generateRandomCardNumber();
        }
    }
}
