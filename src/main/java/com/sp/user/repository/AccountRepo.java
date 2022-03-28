package com.sp.user.repository;

import com.sp.user.data.model.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepo extends CrudRepository<Account, Integer> {

    Optional<Account> findByUsername(String username);
}
