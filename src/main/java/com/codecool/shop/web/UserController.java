package com.codecool.shop.web;

import com.codecool.shop.dao.implementation.UserDaoJdbc;
import com.codecool.shop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserDaoJdbc userDaoJdbc;

    public UserController(UserDaoJdbc userDaoJdbc) {
        this.userDaoJdbc = userDaoJdbc;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        return "modlas/signUpModal";
    }

    @PostMapping("/register")
    public String processRegistrationForm(@RequestParam("firstName") String firstname,
                                          @RequestParam("lastName") String lastName,
                                          @RequestParam("email") String email,
                                          @RequestParam("address") String address,
                                          @RequestParam("password") String password) {
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        userDaoJdbc.add(new User(firstname,lastName,email,encodedPassword,address));
        return "redirect:/";
    }

}
