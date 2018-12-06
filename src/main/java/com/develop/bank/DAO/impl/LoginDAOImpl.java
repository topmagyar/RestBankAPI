package com.develop.bank.DAO.impl;

import com.develop.bank.DAO.LoginDAO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

@Repository
public class LoginDAOImpl implements LoginDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public long login(String username, String password) {
        return 228;
    }
}
