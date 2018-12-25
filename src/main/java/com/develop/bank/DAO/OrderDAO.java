package com.develop.bank.DAO;

import com.develop.bank.model.order.TransferOrder;

import java.util.List;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

public interface OrderDAO {

    TransferOrder addOrder(TransferOrder order);
    List<TransferOrder> getOrders();
    String removeOrder(TransferOrder order);
    TransferOrder getOrder(String field, String value);
}
