package com.codecool.shop.web;

import com.codecool.shop.model.Cart;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.service.CartService;
import com.codecool.shop.service.OrderService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Objects;

@Controller
public class OrderController {
    private final OrderService orderService;
    private final CartService cartService;

    public OrderController(OrderService orderService, CartService cartService) {
        this.orderService = orderService;
        this.cartService = cartService;
    }

    @PostMapping("/order")
    public String saveOrder(){
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        if(!Objects.equals(id, "anonymousUser")){
            orderService.add(new Order(Integer.parseInt(id),cartService.get()));
        }
        return "redirect:/";
    }

}
