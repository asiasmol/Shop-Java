package com.codecool.shop.dao;

import com.codecool.shop.model.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    void addUser(User user);
    User getUser(int id);
    User findUser(int id);

    List<Map<String, Object>> getAll();
}
