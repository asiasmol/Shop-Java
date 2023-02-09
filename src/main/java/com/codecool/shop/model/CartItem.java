package com.codecool.shop.model;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

public class CartItem {
    private Product product;
    private BigDecimal price;
    private String name;
    private int counter;

    public CartItem(Product product) {
        this.product = product;
        this.counter = 1;
        this.price = product.getDefaultPrice();
        this.name = product.getName();
    }

    public void increaseCounter() {
        counter++;
        recalculate();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getCounter() {
        return counter;
    }

    public void decreaseCounter() {
        if (counter > 0)
            counter--;
        recalculate();
    }

    private void recalculate() {
        price = product.getDefaultPrice().multiply(new BigDecimal(counter));
    }

    public Product getProduct() {
        return product;
    }

    public boolean hasZeroItems() {
        return counter == 0;
    }

}
