package com.develop.bank.services.impl;

import com.develop.bank.DAO.*;
import com.develop.bank.model.Card;
import com.develop.bank.model.ConnectionInfo;
import com.develop.bank.model.order.IncreaseOrder;
import com.develop.bank.model.order.TransferOrder;
import com.develop.bank.model.User;
import com.develop.bank.model.ValidToken;
import com.develop.bank.services.OrderService;
import com.develop.bank.util.CryptTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

@Service
@Transactional(readOnly = false)
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private TokenDAO tokenDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ConnectionDAO connectionDAO;

    @Autowired
    private CardDAO cardDAO;

    @Override
    public TransferOrder createOrder(String token, TransferOrder order) {
        User user = getUserByToken(token);
        if (checkTokenValid(token) && user != null) {
            orderDAO.addOrder(order);
        }
        return order;
    }

    @Override
    public String removeOrder(String token, String orderId) {
        if (checkTokenValid(token)) {
            TransferOrder order = orderDAO.getOrder("id",orderId);
            orderDAO.removeOrder(order);
            return "Success";
        }
        return "Error";
    }

    @Override
    public List<TransferOrder> getOrders(String token) {
        if (checkTokenValid(token)) {
            return orderDAO.getOrders();
        }
        return null;
    }

    @Override
    public TransferOrder getOrder(String token, String orderId) {
        if (checkTokenValid(token)) {
            return orderDAO.getOrder("id", orderId);
        }
        return null;
    }

    @Override
    public Card increaseOrder(String username, String token, IncreaseOrder increaseOrder) {
        ConnectionInfo connectionInfo = connectionDAO.getConnectionInfo(username);
        if (connectionInfo != null) {
            token = new CryptTool().decryptMessageByKey(token, connectionInfo.getInfo());
            User user = getUserByToken(token);
            if (checkTokenValid(token)) {
                Card card = cardDAO.getCard("cardNumber", increaseOrder.getCardNumber());
                if (card.getUserId().equals(user.getId())) {
                    card.setAmount(card.getAmount() + increaseOrder.getAmount());
                    cardDAO.updateCard(card);
                    return card;
                }
            }
        }
        return null;
    }

    boolean checkTokenValid(String token) {
        ValidToken validToken = tokenDAO.get(token);
        return validToken != null;
    }

    User getUserByToken(String token) {
        User user = userDAO.getUser("token", token);
        return user;
    }
}
