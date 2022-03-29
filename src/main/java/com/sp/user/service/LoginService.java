package com.sp.user.service;

import com.sp.user.data.model.Account;
import com.sp.user.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LoginService implements UserDetailsService {

    @Autowired
    AccountRepo accountRepo;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean login(Account iAccount) {
        Account account = null;
        Optional<Account> optAccount = accountRepo.findByUsername(iAccount.getUsername());
        if(optAccount.isPresent())
        {
            account = optAccount.get();
            return account.getPassword().matches(iAccount.getPassword());
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = null;
        Optional<Account> optAccount = accountRepo.findByUsername(username);
        if(optAccount.isPresent())
        {
            account = optAccount.get();

        }

        UserDetails user = new User(username, passwordEncoder.encode(account.getPassword()), Arrays.asList(new SimpleGrantedAuthority("USER")));
        return user;
    }

 }
