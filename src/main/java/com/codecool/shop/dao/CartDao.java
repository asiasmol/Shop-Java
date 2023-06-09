package com.codecool.shop.dao;

import com.codecool.shop.model.Product;

import java.util.List;

public interface CartDao {
    List get();

    void add(Product product);

    void remove(Product product);

    void edit();

    void checkout();
}
