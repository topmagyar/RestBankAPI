package com.develop.bank.DAO.impl;

import com.develop.bank.DAO.TokenDAO;
import com.develop.bank.model.ValidToken;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

@Repository
public class TokenDAOImpl implements TokenDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(String token) {
        ValidToken validToken = new ValidToken();
        validToken.setToken(token);
        sessionFactory.getCurrentSession().save(validToken);
    }

    @Override
    public ValidToken get(String token) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ValidToken.class)
                .add(Restrictions.eq("token", token));
        Object result = criteria.uniqueResult();
        return (ValidToken) result;
    }
}
