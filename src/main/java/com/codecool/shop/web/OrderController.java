package com.codecool.shop.web;

import com.codecool.shop.model.Product;
import com.codecool.shop.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/add/order/{id}")
    public String addOrderToOrderHistory(@PathVariable("id") int id){
        Optional<Product> oProduct = Optional.ofNullable(orderService.add(order));
        return null;
    }
}
