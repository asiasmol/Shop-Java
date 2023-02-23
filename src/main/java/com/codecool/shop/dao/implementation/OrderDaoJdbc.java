package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrderDaoJdbc implements OrderDao {

    private final JdbcTemplate jdbc;

    public OrderDaoJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


    @Override
    public void add(Order order) {
        jdbc.update("INSERT INTO users (user_id, products, data, final_price, is_paid) VALUES (?,?, ?, ?, ?)",
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
        return jdbc.query("SELECT * FROM orders WHERE user_id = userId",
                OrderDaoJdbc::mapRow);
    }

    private static Order mapRow(ResultSet rs,int rowNum) throws SQLException {
        return new Order(rs.getInt("id"), (Cart) rs.getObject("products"));
    }




}
