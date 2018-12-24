package com.develop.bank.services;

import com.develop.bank.model.Order;

import java.util.List;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

public interface OrderService {

    Order createOrder(String token, Order order);
    String removeOrder(String token, String orderId);
    List<Order> getOrders(String token);
    Order getOrder(String token, String orderId);
}
