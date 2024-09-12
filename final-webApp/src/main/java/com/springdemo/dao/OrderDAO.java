package com.springdemo.dao;

import com.springdemo.entity.Order;
import com.springdemo.entity.Product;

import java.util.List;

public interface OrderDAO {
    public List<Order> getOrders();

    public void saveOrder(Order theOrder);

    public void deleteOrder(int theId);


    Order getOrderById(int orderId);
}
