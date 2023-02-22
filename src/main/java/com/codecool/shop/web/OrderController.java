package com.codecool.shop.web;

import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.CartService;
import com.codecool.shop.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

public class OrderController {



    private final CartService cartService;
    private final OrderService orderService;

    public OrderController(CartService cartService, OrderService orderService) {
        this.cartService = cartService;
        this.orderService = orderService;
    }

//    @GetMapping("/order_history/{id}")
//    public String addOrderToHistory(@PathVariable("id") int id) {
//        Optional<Order> oOrder = Optional.ofNullable(orderService.);
//        if(oOrder.isPresent()){
//            Order order = oOrder.get();
//            order.add();
//        }
//        return "redirect:/";
//    }

}
