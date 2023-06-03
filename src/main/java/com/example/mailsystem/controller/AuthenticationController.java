package com.example.mailsystem.controller;

import com.example.mailsystem.model.Token;
import com.example.mailsystem.model.User;
import com.example.mailsystem.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/authenticate")
    public Token authenticateUser(@RequestBody User user) {
        return authenticationService.authenticate(user);
    }

}
