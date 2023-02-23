package com.codecool.shop.service;

import com.codecool.shop.dao.implementation.UserDaoJdbc;
import com.codecool.shop.web.security.Roles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import com.codecool.shop.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserDaoJdbc userDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public MyUserDetailsService(UserDaoJdbc userDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            Optional<User> user = userDao.get(email);
            if (user.isEmpty()) {
                throw new UsernameNotFoundException(email);
            }
            User user1 = user.get();
            List<GrantedAuthority> authorities = Collections.emptyList();
            if (user1.IsUser()) {
                authorities = AuthorityUtils.createAuthorityList(Roles.USER);
            }
            return new org.springframework.security.core.userdetails.User(user1.getId().toString(), user1.getPassword(), authorities);
    }
    public boolean registration(String firstname, String lastName, String email, String password, String address){
        Optional<User> user = userDao.get(email);
        if(user.isEmpty()){
            String encodedPassword = bCryptPasswordEncoder.encode(password);
            userDao.add(new User(firstname,lastName,email,encodedPassword,address));
            return true;
        }
        return false;
    }

}
