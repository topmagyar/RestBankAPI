package com.develop.bank.services;

import com.develop.bank.model.User;

import java.util.List;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

public interface UserService {
    User save(User user);
    List<User> getUsers(String token);
}
