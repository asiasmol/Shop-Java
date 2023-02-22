package com.codecool.shop.service;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;

import java.util.List;

public class OrderService {

    private OrderDao orderDao;

    public List<Order> getOrdersBy(int userId) {
        return orderDao.getOrdersBy(userId);
    }


}
