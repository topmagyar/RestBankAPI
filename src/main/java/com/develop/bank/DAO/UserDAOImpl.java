package com.develop.bank.DAO;

import com.develop.bank.DAO.UserDAO;
import com.develop.bank.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public long save(User user) {
        sessionFactory.getCurrentSession().save(user);
        return user.getId();
    }
}
