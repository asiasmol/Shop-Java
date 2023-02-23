package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.lang.model.element.Element;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class OrderDaoJdbc implements OrderDao {

    private final JdbcTemplate jdbc;
    private static ProductDaoJdbc productDaoJdbc;

    public OrderDaoJdbc(JdbcTemplate jdbc, ProductDaoJdbc productDaoJdbc) {
        this.jdbc = jdbc;
        this.productDaoJdbc = productDaoJdbc;
    }


    @Override
    public void add(Order order) {
        jdbc.update("INSERT INTO orders (user_id, products, data, final_price, is_paid) VALUES (?,?, ?, ?, ?)",
                order.getUserId(), order.getProducts(), order.getDate(), order.getFinalPrice(), order.getPaymentStatus()
                );
    }



    @Override
    public List<Order> getAll() {
        return jdbc.query("SELECT * FROM orders",
                OrderDaoJdbc::mapRow);
    }

    @Override
    public List<Order> getAllOrdersForUser(int userId) {
        return jdbc.query("SELECT * FROM orders WHERE user_id = ?",
                OrderDaoJdbc::mapRow, userId);
    }

    private static Order mapRow(ResultSet rs,int rowNum) throws SQLException {
        String string = rs.getString("products");
        String[] list = string.split("");
        List<CartItem> itemList = new ArrayList<>();
        for (String id : list) {
            itemList.add(new CartItem(productDaoJdbc.find(Integer.parseInt(id))));
        }
        Cart cart = new Cart(itemList);
        return new Order(rs.getInt("user_id"), cart);
    }







}
