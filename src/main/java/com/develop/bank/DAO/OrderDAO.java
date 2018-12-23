package com.develop.bank.DAO;

import com.develop.bank.model.Order;

import java.util.List;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

public interface OrderDAO {

    Order addOrder(Order order);
    List<Order> getOrders();
    String removeOrder(Order order);
    Order getOrder(String field, String value);
}
