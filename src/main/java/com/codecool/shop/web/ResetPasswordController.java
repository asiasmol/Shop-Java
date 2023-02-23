package com.codecool.shop.web;

import com.codecool.shop.model.User.PasswordResetToken;
import com.codecool.shop.model.User.User;
import com.codecool.shop.service.MyUserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;


@Controller
public class ResetPasswordController {

    private MyUserDetailsService userService;

    public ResetPasswordController(MyUserDetailsService userService) {
        this.userService = userService;
    }


    @GetMapping("/resetPassword")
    public String showResetPasswordForm(@RequestParam("token") String token, Model model) {
        if (!userService.TokenIsOk(token)){
            model.addAttribute("Wrongtoken", "Wrong Link!");
        }
        model.addAttribute("token",token);
        return "resetPassword";
    }
    @PostMapping("/resetPassword")
    public String processResetPasswordForm(@RequestParam("token") String token, @RequestParam("password") String password,Model model,RedirectAttributes redirectAttributes) {
        if (!userService.TokenIsOk(token)){
            model.addAttribute("Wrongtoken", "Wrong Link!");
            return "resetPassword";
        }else{
            redirectAttributes.addFlashAttribute("resetPasswordSucces", "Your Password Reset Succesfoul ;D");
            userService.resetPassword(token,password);
            return "redirect:/";
        }
    }
}
