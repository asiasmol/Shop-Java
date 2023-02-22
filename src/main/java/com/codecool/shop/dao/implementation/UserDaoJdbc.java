package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class UserDaoJdbc implements UserDao {
    private final JdbcTemplate jdbc;

    public UserDaoJdbc(JdbcTemplate jdbc) {;
        this.jdbc = jdbc;
    }

    @Override
    public void add(User user) {
        jdbc.update("INSERT INTO users (name, surname,email,password,address) VALUES (?,?, ?, ?, ?)",
                user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), user.getAddress());
    }
    @Override
    public User get(String email) {
        return jdbc.queryForObject("SELECT * FROM users WHERE email = ?",
                UserDaoJdbc::mapRow, email);
    }


    @Override
    public List<User> getAll() {
        return jdbc.query("SELECT * FROM products ORDER BY id",
                UserDaoJdbc::mapRow);
    }

    private static User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(rs.getLong("id"),rs.getString("name"),rs.getString("surname"), rs.getString("email"),rs.getString("password"),rs.getString("address") );
    }

    @Override
    public User find(int id) {
        return jdbc.queryForObject("SELECT * FROM user WHERE id = ?",
                UserDaoJdbc::mapRow, id);
    }

}
