package com.sp.user.repository;

import java.util.Optional;

import com.sp.user.data.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Integer> {
    Optional<User> findById(Integer id);
}
