package com.develop.bank.controllers;

import com.develop.bank.model.User;
import com.develop.bank.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

@RestController
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ResponseBody
    public User registerUser(@RequestHeader("password") String password, @RequestBody User user) {
        user.setPassword(password);
        User createdUser = userService.save(user);
        return createdUser;
    }
}
