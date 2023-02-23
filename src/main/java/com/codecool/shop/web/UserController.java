package com.codecool.shop.web;

import com.codecool.shop.dao.implementation.UserDaoJdbc;
import com.codecool.shop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserDaoJdbc userDaoJdbc;

    public UserController(UserDaoJdbc userDaoJdbc) {
        this.userDaoJdbc = userDaoJdbc;
    }

    @PostMapping("/register")
    public String processRegistrationForm(RedirectAttributes redirectAttributes, @RequestParam("firstName") String firstname,
                                          @RequestParam("lastName") String lastName,
                                          @RequestParam("email") String email,
                                          @RequestParam("address") String address,
                                          @RequestParam("password") String password){
            Optional<User> user = userDaoJdbc.get(email);
            if(user.isEmpty()){
                String encodedPassword = bCryptPasswordEncoder.encode(password);
                userDaoJdbc.add(new User(firstname,lastName,email,encodedPassword,address));
                redirectAttributes.addFlashAttribute("registrationSucces", "Registration is Succes!");

            }
            else{
                redirectAttributes.addFlashAttribute("acountExistError", "this account already exist!");
            }
            return "redirect:/";

    }
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
    @GetMapping("/login")
    public String login(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("wrongLoginDataError", "wrong eamil or password!");
        return "redirect:/";
    }
}
