package com.develop.bank.controllers;

import com.develop.bank.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public String loginUser(@RequestHeader("username") String username,
                            @RequestHeader("password") String password) {
        String token = loginService.login(username, password);
        return "User with username " + username + " has been logged in with status " + token;
    }
}
