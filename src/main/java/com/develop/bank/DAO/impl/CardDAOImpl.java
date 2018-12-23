package com.develop.bank.DAO.impl;

import com.develop.bank.DAO.CardDAO;
import com.develop.bank.model.Card;
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
public class CardDAOImpl implements CardDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Card saveCard(Card card) {
        sessionFactory.getCurrentSession().save(card);
        return card;
    }

    @Override
    public String removeCard(String cardNumber) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Card.class)
                .add(Restrictions.eq("cardNumber", cardNumber));
        Card result = (Card) criteria.uniqueResult();
        sessionFactory.getCurrentSession().delete(result);
        return "Success";
    }

    @Override
    public String updateCard(Card card) {
        return null;
    }

    @Override
    public Card getCard(String cardNumber) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Card.class)
                .add(Restrictions.eq("cardNumber", cardNumber));
        Object result = criteria.uniqueResult();
        return (Card) result;
    }

    @Override
    public List<Card> getCards() {
        CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Card> criteria = builder.createQuery(Card.class);
        criteria.from(Card.class);
        List<Card> data = sessionFactory.getCurrentSession().createQuery(criteria).getResultList();
        return data;
    }
}
