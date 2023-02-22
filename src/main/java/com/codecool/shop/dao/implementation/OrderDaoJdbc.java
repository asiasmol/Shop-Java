package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrderDaoJdbc implements OrderDao{

    private final JdbcTemplate jdbc;
    private static UserDaoJdbc userDaoJdbc;

    public OrderDaoJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Order> add(Order order) {
        return jdbc.query("INSERT INTO orders VALUES id = ?, user_email = ?, products = ?, data = ?, final_price = ?, is_paid = ?",
                OrderDaoJdbc::mapRow, order.getId(), order.getUserId(), order.getProducts(), order.getDateOfOrder(), order.getFinalPrice(), order.isPaid());
    }

    @Override
    public List<Order> getOrdersBy(int userId) {
        return jdbc.query("SELECT * FROM orders WHERE user_id = ? ORDER BY date ",
                OrderDaoJdbc::mapRow, userId);
    }

    private static Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Order(rs.getInt("id"), );
    }
}
