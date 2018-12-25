package com.develop.bank.services;

import com.develop.bank.model.Card;
import com.develop.bank.model.order.DecreaseOrder;
import com.develop.bank.model.order.IncreaseOrder;
import com.develop.bank.model.order.TransferOrder;

import java.util.List;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

public interface OrderService {

    TransferOrder createOrder(String username, String token, TransferOrder order);
    String removeOrder(String username, String token, String orderId);
    List<TransferOrder> getOrders(String username, String token);
    TransferOrder getOrder(String username, String token, String orderId);
    Card increaseOrder(String username, String token, IncreaseOrder increaseOrder);
    Card decreaseOrder(String username, String token, DecreaseOrder increaseOrder);
}
