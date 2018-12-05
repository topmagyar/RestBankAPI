package com.develop.bank.services.impl;

import com.develop.bank.DAO.UserDAO;
import com.develop.bank.model.User;
import com.develop.bank.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Transactional
    @Override
    public long save(User user) {
        return userDAO.save(user);
    }
}
