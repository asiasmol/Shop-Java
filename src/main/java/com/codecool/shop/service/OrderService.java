package com.codecool.shop.service;


import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderService {
    private OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void add(Order order){
        orderDao.add(order);
    }

    public List<Order> getAll(){
        return orderDao.getAll();
    }

    public List<Order> getAllOrdersForUser(int userId){
        return orderDao.getAllOrdersForUser(userId);
    }
}
