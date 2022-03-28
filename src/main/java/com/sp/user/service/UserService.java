package com.sp.user.service;

import com.sp.user.data.model.User;
import com.sp.user.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserService {

    @Autowired
    UserRepo userRepo;

    public Optional<User> getUser(Integer uuid) {
        return userRepo.findById(uuid);
    }

    public User updateUser(User iUser) {
        User user = null;

        Optional<User> optUser = userRepo.findById(iUser.getAccountId());
        if(optUser.isPresent())
        {
            user = optUser.get();
        } else {
            user = new User();
            user.setAccountId(iUser.getAccountId());
        }
        user.setEmail(iUser.getEmail());
        user.setName(iUser.getName());
        user.setPhone(iUser.getPhone());

        user = userRepo.save(user);
        return user;
    }
}
