package com.example.mailsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Token {
    private final String token;
//    public Token(String token) {
//        this.token = token;
//    }
//    public String getToken() {
//        return token;
//    }
}
