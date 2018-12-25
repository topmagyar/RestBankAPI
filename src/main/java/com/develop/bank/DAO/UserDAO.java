package com.develop.bank.DAO;

import com.develop.bank.model.User;

import java.util.List;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

public interface UserDAO {

    User save(User user);
    List<User> getUsers();
    User getUser(String field, String value);
}
