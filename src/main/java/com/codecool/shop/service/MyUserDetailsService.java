package com.codecool.shop.service;

import com.codecool.shop.dao.implementation.PasswordResetTokenJdbc;
import com.codecool.shop.dao.implementation.UserDaoJdbc;
import com.codecool.shop.model.User.PasswordResetToken;
import com.codecool.shop.web.security.Roles;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import com.codecool.shop.model.User.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserDaoJdbc userDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private PasswordResetTokenJdbc passwordResetTokenJdbc;

    private EmailService emailService;

    public MyUserDetailsService(UserDaoJdbc userDao, BCryptPasswordEncoder bCryptPasswordEncoder, PasswordResetTokenJdbc passwordResetTokenJdbc, EmailService emailService) {
        this.userDao = userDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.passwordResetTokenJdbc = passwordResetTokenJdbc;
        this.emailService = emailService;
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
    public boolean forgotPassword(String userEmail , HttpServletRequest request){
        Optional<User> Optionaluser = userDao.get(userEmail);
        if (Optionaluser.isEmpty()) {
            return false;
        }
        User user = Optionaluser.get();

        // Generowanie tokena resetującego
        String token = UUID.randomUUID().toString();

        passwordResetTokenJdbc.createPasswordResetTokenForUser(user, token);
//
        // Wysyłanie maila z linkiem resetującym hasło
        String appUrl = request.getScheme() + "://" + request.getServerName()+":8888";
        String resetPasswordUrl = appUrl + "/resetPassword?token=" + token;

        String subject = "Reset hasła";
        String body = "Aby zresetować hasło kliknij w poniższy link:\n" + resetPasswordUrl;

        emailService.sendEmail(user.getEmail(), subject, body);
        return true;
    }
    public boolean TokenIsOk(String token){
        Optional<PasswordResetToken> optionalPasswordResetToken = passwordResetTokenJdbc.getPasswordResetToken(token);
        if (optionalPasswordResetToken.isEmpty()) return false;
        return true;
    }
    public void resetPassword(String token, String password){
        PasswordResetToken passwordResetToken = passwordResetTokenJdbc.getPasswordResetToken(token).get();
        User user = passwordResetToken.getUser();
        userDao.changeUserPassword(user, password);
        passwordResetTokenJdbc.deletePasswordResetToken(passwordResetToken);
    }
}
