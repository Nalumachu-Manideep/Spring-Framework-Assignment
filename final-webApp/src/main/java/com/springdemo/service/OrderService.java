package com.springdemo.service;

import com.springdemo.entity.Order;

import java.util.List;

public interface OrderService {
    public List<Order> getOrders();

    public void saveOrder(Order theOrder);

    public void deleteOrder(int theId);

    public Order getOrderByID(int orderId);
}
