package com.develop.bank.DAO.impl;

import com.develop.bank.DAO.LoginDAO;
import com.develop.bank.model.User;
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
        User user = sessionFactory.getCurrentSession().byNaturalId(User.class)
                .using("username",username)
                .load();
        return user.getId();
    }
}
