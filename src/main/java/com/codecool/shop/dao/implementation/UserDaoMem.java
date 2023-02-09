package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserDaoMem implements UserDao {

    private JdbcTemplate jdbc;

    public UserDaoMem(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void addUser(User user) {
        jdbc.update("INSERT INTO users (name, surname, email, password, address) VALUES (?, ?, ?, ?, ?)", user.getName(), user.getSurname(),
                user.getEmail(), user.getPassword(), user.getAdress());

    }

    @Override
    public User getUser(int id) {
        return null;
    }

    @Override
    public User findUser(int id) {
        return null;
    }

    @Override
    public List<Map<String, Object>> getAll() {
        return jdbc.queryForList("SELECT * FROM users");
    }
}
