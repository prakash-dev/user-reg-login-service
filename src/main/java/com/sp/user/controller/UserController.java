package com.sp.user.controller;

import com.sp.user.service.UserService;
import com.sp.user.data.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class UserController extends BaseController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{uuid}")
    ResponseEntity<User> getUser(@PathVariable Integer uuid) {

        Optional<User> user = userService.getUser(uuid);
        if (!user.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User Not Found");
        }
        return new ResponseEntity<User>(user.get(), HttpStatus.OK);
    }

    @PostMapping("/users/{uuid}")
    ResponseEntity<User> updateUser(@PathVariable Integer uuid, @RequestBody User iUser) {
        try {
            iUser.setAccountId(uuid);
            User user = userService.updateUser(iUser);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Unable to update user", ex);
        }
    }
}
