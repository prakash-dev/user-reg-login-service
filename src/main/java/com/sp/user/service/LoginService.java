package com.sp.user.service;

import com.sp.user.data.model.Account;
import com.sp.user.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoginService {

    @Autowired
    AccountRepo accountRepo;

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
}
