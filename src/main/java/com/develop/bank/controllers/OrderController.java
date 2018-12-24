package com.develop.bank.controllers;

import com.develop.bank.model.Order;
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

    @PostMapping("/orders/add")
    @ResponseBody
    public Order addOrder(@RequestHeader("token") String token, @RequestBody Order order) {
        Order o = orderService.createOrder(token, order);
        return o;
    }

    @GetMapping("/orders/get")
    @ResponseBody
    public List<Order> getOrders(@RequestHeader("token") String token) {
        return orderService.getOrders(token);
    }

    @GetMapping("orders/get/{id}")
    @ResponseBody
    public Order getOrder(@RequestHeader("token") String token, @PathVariable("id") String id) {
        return orderService.getOrder(token, id);
    }



}
