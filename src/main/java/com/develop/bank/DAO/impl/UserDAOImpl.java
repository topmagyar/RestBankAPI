package com.develop.bank.DAO.impl;

import com.develop.bank.DAO.UserDAO;
import com.develop.bank.model.User;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

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

    public List<User> getUsers() {
        CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        criteria.from(User.class);
        List<User> data = sessionFactory.getCurrentSession().createQuery(criteria).getResultList();
        return data;
    }

    public User getUser(String field, String value) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class)
                .add(Restrictions.eq(field, value));
        Object result = criteria.uniqueResult();
        return (User) result;
    }
}
