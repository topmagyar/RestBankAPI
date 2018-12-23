package com.develop.bank.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

@RestController
public class OrderController {

    @PostMapping("/orders/add")
    @ResponseBody
    public String addOrder(@RequestHeader("token") String token) {
        return null;
    }
}
