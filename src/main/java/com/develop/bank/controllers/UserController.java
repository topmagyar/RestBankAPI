package com.develop.bank.controllers;

//import com.develop.bank.model.User;
//import com.develop.bank.services.UserService;
import com.develop.bank.model.User;
import com.develop.bank.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

@RestController
public class UserController {

    @Autowired
    private UserService userService;

//    @PostMapping("/user")
//    public ResponseEntity<?> saveUser(@RequestBody User user) {
//        User user = userService.save(user);
//
//        return ResponseEntity.ok().body("User has been saved with id: " + id);
//    }
//
    @GetMapping("/users")
    @ResponseBody
    public List<User> getUsers(@RequestHeader("token") String token) {
        List<User> users = userService.getUsers(token);
        return users;
    }

    @GetMapping("/bla")
    public String get() {
        System.out.println("CONTROLLER");
        return "BLA";
    }
}
