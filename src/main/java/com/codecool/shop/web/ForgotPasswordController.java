package com.codecool.shop.web;

import com.codecool.shop.service.MyUserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ForgotPasswordController {
    private MyUserDetailsService userService;

    public ForgotPasswordController(MyUserDetailsService userService) {
        this.userService = userService;
    }

    @PostMapping("/forgotPassword")
    public String processForgotPasswordForm(@RequestParam("email") String userEmail, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        if(userService.forgotPassword(userEmail,request)){
            redirectAttributes.addFlashAttribute("sendSucces", "mail Send :)");
        }else{
            redirectAttributes.addFlashAttribute("sendError", "Wrong email!");
        }
        return "redirect:/";
    }
}
