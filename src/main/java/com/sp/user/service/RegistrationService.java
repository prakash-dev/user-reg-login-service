package com.sp.user.service;

import com.sp.user.data.model.Account;
import com.sp.user.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RegistrationService {

    @Autowired
    AccountRepo accountRepo;

    public Account register(Account iAccount) {
        Account account = null;

        Optional<Account> optAccount = accountRepo.findByUsername(iAccount.getUsername());
        if(optAccount.isPresent())
        {
            account = optAccount.get();
        } else {
            account = new Account();
            account.setUsername(iAccount.getUsername());
        }
        account.setPassword(iAccount.getPassword());

        account = accountRepo.save(account);
        return account;
    }
}
