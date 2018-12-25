package com.develop.bank.controllers;

import com.develop.bank.model.Card;
import com.develop.bank.model.order.DecreaseOrder;
import com.develop.bank.model.order.IncreaseOrder;
import com.develop.bank.model.order.TransferOrder;
import com.develop.bank.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/transfers/add")
    @ResponseBody
    public TransferOrder addOrder(@RequestHeader("token") String token, @RequestBody TransferOrder order) {
        TransferOrder o = orderService.createOrder(token, order);
        return o;
    }

    @GetMapping("/transfers/get")
    @ResponseBody
    public List<TransferOrder> getOrders(@RequestHeader("token") String token) {
        return orderService.getOrders(token);
    }

    @GetMapping("/transfers/get/{id}")
    @ResponseBody
    public TransferOrder getOrder(@RequestHeader("token") String token, @PathVariable("id") String id) {
        return orderService.getOrder(token, id);
    }

    @PostMapping("/transfers/increase")
    @ResponseBody
    public Card increaseCard(@RequestHeader("username") String username, @RequestHeader("token") String token,
                             @RequestBody IncreaseOrder increaseOrder) {
        return orderService.increaseOrder(username, token, increaseOrder);
    }

    @PostMapping("/transfers/decrease")
    @ResponseBody
    public Card decreaseCard(@RequestHeader("username") String username, @RequestHeader("token") String token,
                             @RequestBody DecreaseOrder decreaseOrder) {
        return orderService.decreaseOrder(username, token, decreaseOrder);
    }

}
