package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.PasswordResetTokenDao;
import com.codecool.shop.model.User.PasswordResetToken;
import com.codecool.shop.model.User.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Repository
public class PasswordResetTokenJdbc implements PasswordResetTokenDao {

    private final JdbcTemplate jdbc;
    private final UserDaoJdbc userDaoJdbc;

    public PasswordResetTokenJdbc(JdbcTemplate jdbc, UserDaoJdbc userDaoJdbc) {
        this.jdbc = jdbc;
        this.userDaoJdbc = userDaoJdbc;
    }

    @Override
    public void createPasswordResetTokenForUser(User user, String token) {
        jdbc.update("INSERT INTO password_reset_token (token, user_email) VALUES (?,?)",
                token,user.getEmail());
    }

    @Override
    public Optional<PasswordResetToken> getPasswordResetToken(String token) {
        try{
            return Optional.ofNullable(jdbc.queryForObject("SELECT * FROM password_reset_token WHERE token = ?", this::mapPasswordResetToken, token));
        }catch (Exception e){
            return Optional.empty();
        }

    }

    @Override
    public void deletePasswordResetToken(PasswordResetToken passwordResetToken) {
        System.out.println(passwordResetToken.getToken());
        jdbc.update("DELETE FROM password_reset_token WHERE token = ?",passwordResetToken.getToken());
    }

    private PasswordResetToken mapPasswordResetToken(ResultSet rs, int rowNum) throws SQLException {
        return new PasswordResetToken(userDaoJdbc.get(rs.getString("user_email")),rs.getString("token"));
    }
}
