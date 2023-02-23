package com.codecool.shop.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value="session", proxyMode= ScopedProxyMode.TARGET_CLASS)
public class  Cart {
    private List<CartItem> cartItems = new ArrayList<>();
    private int counter = 0;
    private BigDecimal sum = BigDecimal.ZERO;

    public void add(Product product) {
        boolean notFound = true;
        for (CartItem item : cartItems) {
            if (item.getProduct().getName().equals(product.getName())) {
                item.increaseCounter();
                notFound = false;
            }
        }
        if (notFound) {
            cartItems.add(new CartItem(product));
        }
        recalculatePriceAndCounter();
    }

    public void remove(Product product) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getName().equals(product.getName())) {
                cartItems.remove(item);
                recalculatePriceAndCounter();
                break;
            }
        }
    }

    private void recalculatePriceAndCounter() {
        int tempCounter = 0;
        BigDecimal tempPrice = BigDecimal.ZERO;

        for (CartItem item : cartItems) {
            tempCounter += item.getCounter();
            tempPrice = tempPrice.add(item.getPrice());
        }
        this.counter = tempCounter;
        this.sum = tempPrice;
    }

    public List<CartItem> getAll(){
        return cartItems;
    }

    public String getSum() {
        return String.valueOf(sum);
    }

    public BigDecimal getSumBigDecimal(){
        return sum;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void clearCart(){
        cartItems.clear();
        recalculatePriceAndCounter();
    }
}

