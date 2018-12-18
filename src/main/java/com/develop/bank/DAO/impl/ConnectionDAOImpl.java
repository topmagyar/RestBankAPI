package com.develop.bank.DAO.impl;

import com.develop.bank.DAO.ConnectionDAO;
import com.develop.bank.model.ConnectionInfo;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

@Repository
public class ConnectionDAOImpl implements ConnectionDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void save(String username, String secretKey) {
        ConnectionInfo connectionInfo = new ConnectionInfo();
        connectionInfo.setInfo(secretKey);
        connectionInfo.setUsername(username);

        sessionFactory.getCurrentSession().save(connectionInfo);
    }

    @Override
    public ConnectionInfo getConnectionInfo(String username) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ConnectionInfo.class)
                .add(Restrictions.eq("username", username));
        Object result = criteria.uniqueResult();
        return (ConnectionInfo) result;
    }

    @Override
    public void remove(String username) {
        ConnectionInfo connectionInfo = getConnectionInfo(username);
        sessionFactory.getCurrentSession().delete(connectionInfo);
    }


}
