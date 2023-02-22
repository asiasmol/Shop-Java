package com.codecool.shop.service;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.UserDaoJdbc;
import com.codecool.shop.web.security.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import com.codecool.shop.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserDaoJdbc userDao;

    public MyUserDetailsService(UserDaoJdbc userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            Optional<User> user = userDao.get(email);
            if (user.isEmpty()) {
                throw new UsernameNotFoundException(email);
            }
            User user1 = user.get();
            List<GrantedAuthority> authorities = Collections.emptyList();
            if (user1.isAdmin()) {
                authorities = AuthorityUtils.createAuthorityList(Roles.ADMIN);
            }
            return new org.springframework.security.core.userdetails.User(user1.getId().toString(), user1.getPassword(), authorities);

    }
}
