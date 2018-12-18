package com.develop.bank.DAO.impl;

import com.develop.bank.DAO.ConnectionDAO;
import com.develop.bank.DAO.LoginDAO;
import com.develop.bank.model.User;
import com.develop.bank.util.CryptTool;
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

    @Autowired
    private ConnectionDAO connectionDAO;

    public String login(String username, String password) {
        User user = sessionFactory.getCurrentSession().byNaturalId(User.class)
                .using("username",username)
                .load();

        String secretKey = connectionDAO.getInfo(username).getInfo();

        if (user.getPassword().equals(new CryptTool().decryptMessageByKey(password, secretKey))) {
            return user.getToken();
        }
        return user.getToken();
    }
}
