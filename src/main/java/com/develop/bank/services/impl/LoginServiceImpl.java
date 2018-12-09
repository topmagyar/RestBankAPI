package com.develop.bank.services.impl;

import com.develop.bank.DAO.LoginDAO;
import com.develop.bank.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

@Service
@Transactional(readOnly = true)
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDAO loginDAO;

    @Transactional
    @Override
    public String login(String username, String password) {
        return loginDAO.login(username, password);
    }
}
