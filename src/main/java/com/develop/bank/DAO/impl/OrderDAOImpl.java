package com.develop.bank.DAO.impl;

import com.develop.bank.DAO.OrderDAO;
import com.develop.bank.model.Order;
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
    public Order addOrder(Order order) {
        sessionFactory.getCurrentSession().save(order);
        return order;
    }

    @Override
    public List<Order> getOrders() {
        CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Order> criteria = builder.createQuery(Order.class);
        criteria.from(Order.class);
        List<Order> data = sessionFactory.getCurrentSession().createQuery(criteria).getResultList();
        return data;
    }

    @Override
    public String removeOrder(Order order) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Order.class)
                .add(Restrictions.eq("cardFrom", order.getCardFrom()));
        Order result = (Order) criteria.uniqueResult();
        sessionFactory.getCurrentSession().delete(result);
        return "Success";
    }

    @Override
    public Order getOrder(String field, String value) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Order.class)
                .add(Restrictions.eq(field, value));
        Object result = criteria.uniqueResult();
        return (Order) result;
    }
}
