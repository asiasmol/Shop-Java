package com.codecool.shop.dao;

import com.codecool.shop.model.Order;

import java.util.List;


public interface OrderDao {

    void add(Order order);

    List<Order> getAll();
    List<Order> getAllOrdersForUser(int userId);


}
