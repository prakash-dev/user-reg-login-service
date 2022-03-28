package com.sp.user.controller;

import com.sp.user.data.model.User;
import com.sp.user.service.LoginService;
import com.sp.user.data.model.Account;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class LoginController extends BaseController {

    private final LoginService loginService;

    LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    ResponseEntity<Boolean> login(@RequestBody Account credential) {
        try {
            boolean loggedIn = loginService.login(credential);
            return new ResponseEntity<Boolean>(loggedIn, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Unable to login", ex);
        }
    }
}
