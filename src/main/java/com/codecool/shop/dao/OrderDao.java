package com.codecool.shop.dao;

import com.codecool.shop.model.Order;

import java.util.List;

public interface OrderDao {

    List<Order> add(Order order);

    List<Order> getOrdersBy(int user_id);
}
