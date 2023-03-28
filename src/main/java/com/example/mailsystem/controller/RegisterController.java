package com.example.mailsystem.controller;

import com.example.mailsystem.model.User;
import com.example.mailsystem.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegisterController {

    private RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/register")
    public void addUser(@RequestBody User user) {
        registerService.add(user);
    }
}
