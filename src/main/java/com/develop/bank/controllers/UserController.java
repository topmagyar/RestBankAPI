package com.develop.bank.controllers;

//import com.develop.bank.model.User;
//import com.develop.bank.services.UserService;
import com.develop.bank.model.User;
import com.develop.bank.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        long id = userService.save(user);

        return ResponseEntity.ok().body("User has been saved with id: " + id);
    }

    @GetMapping("/bla")
    public String get() {
        System.out.println("CONTROLLER");
        return "BLA";
    }
}
