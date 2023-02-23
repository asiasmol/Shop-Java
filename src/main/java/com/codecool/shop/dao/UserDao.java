package com.codecool.shop.dao;

import com.codecool.shop.model.User.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    Optional<User> get(String email);
    Optional<User> getById(int id);
    List<User> getAll();
    void add(User user);
    void changeUserPassword(User user, String password);
}
