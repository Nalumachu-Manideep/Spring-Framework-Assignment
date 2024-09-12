package com.springdemo.dao;

import com.springdemo.entity.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO {

    private final SessionFactory sessionFactory;
    @Autowired
    public OrderDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Order> getOrders() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Order> theQuery = currentSession.createQuery("from Order", Order.class);
        return theQuery.getResultList();
    }

    @Override
    public void saveOrder(Order theOrder) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(theOrder);
    }

    @Override
    public void deleteOrder(int theId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query theQuery = currentSession.createQuery("delete from Order where id=:orderId");
        theQuery.setParameter("orderId", theId);
        theQuery.executeUpdate();
    }

    @Override
    public Order getOrderById(int orderId) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(Order.class, orderId);
    }
}
