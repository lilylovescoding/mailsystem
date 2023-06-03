package com.example.mailsystem.service;

import com.example.mailsystem.exception.UserNotExistException;
import com.example.mailsystem.model.Token;
import com.example.mailsystem.model.User;
import com.example.mailsystem.repository.UserRepository;
import com.example.mailsystem.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;


    @Autowired
    public AuthenticationService(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    public Token authenticate(User user) throws UserNotExistException  {
        Authentication auth = null;
        try {
            auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        } catch (AuthenticationException exception) {
            throw new UserNotExistException("User Doesn't Exist11");
        }

        if( auth == null || !auth.isAuthenticated()) {
            throw new UserNotExistException("User Doesn't Exist22");
        }

        return new Token(jwtUtil.generateToken(user.getUsername()));
    }
}
