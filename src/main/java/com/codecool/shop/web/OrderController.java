package com.codecool.shop.web;

import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.CartService;
import com.codecool.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class OrderController {



    private final CartService cartService;
    private final OrderService orderService;

    @Autowired
    public OrderController(CartService cartService, OrderService orderService) {
        this.cartService = cartService;
        this.orderService = orderService;
    }
// TODO
    @GetMapping("/order_history/{id}")
    public String addOrderToHistory(@PathVariable("id") int id) {
        Optional<Order> oOrder = Optional.ofNullable(orderService.);
        if(oOrder.isPresent()){
            Order order = oOrder.get();
            order.add();
        }
        return "redirect:/";
    }



}
