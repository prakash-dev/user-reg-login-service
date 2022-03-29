package com.sp.user.controller;

import com.sp.user.data.model.Account;
import com.sp.user.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class RegistrationController extends BaseController {

    private final RegistrationService registrationService;

    RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/register")
    ResponseEntity<Account> register(@RequestBody Account account) {
        try {
            account = registrationService.register(account);
            return new ResponseEntity<Account>(account, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Unable to register user", ex);
        }
    }
}
