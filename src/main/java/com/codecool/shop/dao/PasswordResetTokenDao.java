package com.codecool.shop.dao;

import com.codecool.shop.model.User.PasswordResetToken;
import com.codecool.shop.model.User.User;

import java.util.Optional;

public interface PasswordResetTokenDao {
    void createPasswordResetTokenForUser(User user, String token);

    Optional<PasswordResetToken> getPasswordResetToken(String token);

    void deletePasswordResetToken(PasswordResetToken passwordResetToken);

}
