package com.codecool.shop.model;


import java.math.BigDecimal;
import java.time.LocalDate;

public class Order extends BaseModel{
    private int userId;
    private Cart cart;
    private LocalDate date;
    private BigDecimal finalPrice;
    private boolean isPaid;

    public Order(int id, Cart cart) {
        super(id);
        this.cart = cart;
        this.finalPrice = cart.getSumBigDecimal();

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
        return cart.getCartItems().stream().map(cartItem -> cartItem.getProduct().getId()).toList().toString();
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
