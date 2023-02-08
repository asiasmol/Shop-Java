package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartDaoMem implements CartDao {
    private List<Product> data = new ArrayList<>();

    public CartDaoMem(){}

    public List get(){
        return data;
    }

    @Override
    public void add(Product product) {
        data.add(product);
    }

    @Override
    public void remove(Product product) {
        data.remove(product);
    }

    @Override
    public void edit() {

    }

    @Override
    public void checkout() {

    }
}
