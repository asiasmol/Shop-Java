package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoJdbc implements UserDao {
    private final JdbcTemplate jdbc;

    public UserDaoJdbc(JdbcTemplate jdbc) {;
        this.jdbc = jdbc;
    }

    @Override
    public void add(User user) {
        jdbc.update("INSERT INTO users (name, surname,email,password,address) VALUES (?,?, ?, ?, ?)",
                user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), user.getAddress());
    }
    @Override
    public Optional<User> get(String email) {
        try{
            return  Optional.ofNullable(jdbc.queryForObject("SELECT * FROM users WHERE email = ?",
                    UserDaoJdbc::mapRow, email));
        }catch (Exception e){
            return Optional.empty();
        }
    }


    @Override
    public List<User> getAll() {
        return jdbc.query("SELECT * FROM products ORDER BY id",
                UserDaoJdbc::mapRow);
    }

    private static User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(rs.getLong("id"),rs.getString("name"),rs.getString("surname"), rs.getString("email"),rs.getString("password"),rs.getString("address") );
    }

}
