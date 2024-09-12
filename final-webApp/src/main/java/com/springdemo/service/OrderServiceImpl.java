package com.springdemo.service;

import com.springdemo.dao.OrderDAO;
import com.springdemo.entity.Order;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import javax.transaction.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    private OrderDAO orderDAO;

    @Autowired
    public OrderServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;

    }

    @Override
    @Transactional
    public List<Order> getOrders() {
        return orderDAO.getOrders();
    }

    @Override
    @Transactional
    public void saveOrder(Order theOrder) {
        orderDAO.saveOrder(theOrder);

    }

    @Override
    @Transactional
    public void deleteOrder(int theId) {
        orderDAO.deleteOrder(theId);
    }

    @Override
    @Transactional
    public Order getOrderByID(int orderId) {

        Order theOrder=orderDAO.getOrderById(orderId);
        //Hibernate uses lazy loading by default for collections and associations. This means that related entities (e.g., the products associated with the order) are not fetched from the database until they are explicitly accessed.
        Hibernate.initialize(theOrder.getProducts());
        return theOrder;
    }

}
