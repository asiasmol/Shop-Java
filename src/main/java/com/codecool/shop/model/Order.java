package com.codecool.shop.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Order extends BaseModel {

    private BigDecimal finalPrice;
    private boolean isPaid;
    private User user;
    private List<Product> products;
    private LocalDate dateOfOrder;


    public Order(int id, User user, List<Product> products) {
        super(id);
        this.user = user;
        this.setFinalPrice(products);
        this.setIsPaid();
        this.setOrderDate();

    }

    public void setFinalPrice(List<Product> products) {
        this.finalPrice = BigDecimal.valueOf(products.stream().mapToDouble(product -> Double.parseDouble(product.getPrice())).sum());
    }

    public void setIsPaid(){

    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public User getUser() {
        return user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public LocalDate getDateOfOrder() {
        return dateOfOrder;
    }

    public void setOrderDate(){
        dateOfOrder = LocalDate.now();
    }


}
