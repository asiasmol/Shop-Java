package com.codecool.shop.model.User;

import java.time.LocalDateTime;
import java.util.Optional;

public class PasswordResetToken {
    private String token;
    private User user;

    public PasswordResetToken(Optional<User> user, String token ) {
        this.user = user.get();
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }



}
