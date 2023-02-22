package com.codecool.shop.service;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;

import java.util.List;

public class OrderService {

    private OrderDao orderDao;
    private User user;

    public List<Order> getOrdersBy(int user_id) {
        return orderDao.getOrdersBy(user_id);
    }


}
