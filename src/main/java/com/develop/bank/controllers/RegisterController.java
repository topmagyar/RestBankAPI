package com.develop.bank.controllers;

import com.develop.bank.model.User;
import com.develop.bank.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

@RestController
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestHeader("password") String password, @RequestBody User user) {
        user.setPassword(password);
        long id = userService.save(user);
        return "User with username " + user.getUsername() + " has been register successfully with id " + id;
    }
}
