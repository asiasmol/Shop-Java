package com.codecool.shop.dao;

import com.codecool.shop.model.User;

import java.util.List;

public interface UserDao {

    User get(String email);
    List<User> getAll();
    public void add(User user);

    User find(int id);


}
