package com.develop.bank.DAO.impl;

import com.develop.bank.DAO.OrderDAO;
import com.develop.bank.model.order.TransferOrder;
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
public class OrderDAOImpl implements OrderDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public TransferOrder addOrder(TransferOrder order) {
        sessionFactory.getCurrentSession().save(order);
        return order;
    }

    @Override
    public List<TransferOrder> getOrders() {
        CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<TransferOrder> criteria = builder.createQuery(TransferOrder.class);
        criteria.from(TransferOrder.class);
        List<TransferOrder> data = sessionFactory.getCurrentSession().createQuery(criteria).getResultList();
        return data;
    }

    @Override
    public String removeOrder(TransferOrder order) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TransferOrder.class)
                .add(Restrictions.eq("cardFrom", order.getCardFrom()));
        TransferOrder result = (TransferOrder) criteria.uniqueResult();
        sessionFactory.getCurrentSession().delete(result);
        return "Success";
    }

    @Override
    public TransferOrder getOrder(String field, String value) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TransferOrder.class)
                .add(Restrictions.eq(field, value));
        Object result = criteria.uniqueResult();
        return (TransferOrder) result;
    }
}
