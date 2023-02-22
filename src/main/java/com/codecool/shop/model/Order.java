package com.codecool.shop.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Order extends BaseModel {

    private BigDecimal finalPrice;
    private boolean isPaid;
    private int userId;
    private List<Product> products;
    private LocalDate dateOfOrder;


    public Order(int id, int userId, List<Product> products) {
        super(id);
        this.isPaid = false;
        this.userId = userId;
        this.products = products;
        setOrderDate();
        setFinalPrice(products);
    }

    public void setFinalPrice(List<Product> products) {
        this.finalPrice = BigDecimal.valueOf(products.stream().mapToDouble(product -> Double.parseDouble(product.getPrice())).sum());
    }

    public void setIsPaid(){
        isPaid = true;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public int getUserId() {
        return userId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public LocalDate getDateOfOrder() {
        return dateOfOrder;
    }

    private void setOrderDate(){
        dateOfOrder = LocalDate.now();
    }


}
