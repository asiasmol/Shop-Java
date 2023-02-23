package com.codecool.shop.model;


import java.math.BigDecimal;
import java.time.LocalDate;

public class Order{
    private int userId;
    private Cart cart;
    private LocalDate date;
    private BigDecimal finalPrice;
    private boolean isPaid;

    public Order(int userId, Cart cart) {
        this.userId = userId;
        this.cart = cart;
        this.finalPrice = cart.getSumBigDecimal();
        setDate();
    }



    public void isPaid(){
        this.isPaid = true;
    }

    private void setDate(){
        this.date = LocalDate.now();
    }

    public int getUserId() {
        return userId;
    }

    public String getProducts() {
        StringBuilder stringBuilder = new StringBuilder();
        cart.getCartItems().forEach(cartItem -> stringBuilder.append(cartItem.getProduct().getId()));
        return stringBuilder.toString();
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public boolean getPaymentStatus() {
        return isPaid;
    }
}
