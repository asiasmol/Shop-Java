package com.codecool.shop.service;


import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    private ProductDao productDao;
    private Cart cart;

    public CartService(ProductDao productDao, Cart cart) {
        this.productDao = productDao;
        this.cart = cart;
    }

    public String getSum() {
        return cart.getSum();
    }

    public List<CartItem> getAll() {
        return cart.getAll();
    }
    public void add(Product product) {
        cart.add(product);
    }

    public void remove(Product product) {
        cart.remove(product);
    }

    public void clearCart() {
        cart.clearCart();
    }
}
