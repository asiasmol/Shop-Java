package com.codecool.shop.web;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    private UserDao userDao;

    @Autowired
    public UserController(UserDao userDao){
        this.userDao = userDao;
    }
    @PostMapping("/register")
    public String registerUser(@RequestParam("firstName") String firstName, @RequestParam("lastName")String lastName,
                               @RequestParam("email") String email, @RequestParam("password") String password,
                               @RequestParam("address") String address){
        User userToAdd = new User(firstName, lastName, email, password, address);
        userDao.addUser(userToAdd);
        return "redirect:/";
    }

    @GetMapping("/users")
    @ResponseBody
    public List<Map<String, Object>> getUsers(){
        return userDao.getAll();
    }
}
