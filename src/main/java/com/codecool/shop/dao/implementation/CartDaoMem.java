package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CartDaoMem implements CartDao {
    private final JdbcTemplate jdbc;

    public CartDaoMem(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List get() {
        return null;
    }

    @Override
    public void add(Product product) {

    }

    @Override
    public void remove(Product product) {

    }

    @Override
    public void edit() {

    }

    @Override
    public void checkout() {

    }
}
