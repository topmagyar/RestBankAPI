package com.develop.bank.services.impl;

import com.develop.bank.DAO.OrderDAO;
import com.develop.bank.DAO.TokenDAO;
import com.develop.bank.DAO.UserDAO;
import com.develop.bank.model.Order;
import com.develop.bank.model.User;
import com.develop.bank.model.ValidToken;
import com.develop.bank.services.OrderService;
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

    @Override
    public Order createOrder(String token, Order order) {
        User user = getUserByToken(token);
        if (checkTokenValid(token) && user != null) {
            orderDAO.addOrder(order);
        }
        return order;
    }

    @Override
    public String removeOrder(String token, String orderId) {
        if (checkTokenValid(token)) {
            Order order = orderDAO.getOrder("id",orderId);
            orderDAO.removeOrder(order);
            return "Success";
        }
        return "Error";
    }

    @Override
    public List<Order> getOrders(String token) {
        if (checkTokenValid(token)) {
            return orderDAO.getOrders();
        }
        return null;
    }

    @Override
    public Order getOrder(String token, String orderId) {
        if (checkTokenValid(token)) {
            return orderDAO.getOrder("id", orderId);
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
