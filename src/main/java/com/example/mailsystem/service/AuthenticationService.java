package com.example.mailsystem.service;

import com.example.mailsystem.exception.UserNotExistException;
import com.example.mailsystem.model.Token;
import com.example.mailsystem.model.User;
import com.example.mailsystem.repository.UserRepository;
import com.example.mailsystem.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

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
        } catch (BadCredentialsException exception) {
            logger.error("BadCredentialsException: ", exception);
            throw new BadCredentialsException("Bad Credentials");
        } catch (AuthenticationException exception) {
            logger.error("AuthenticationException: ", exception);
            throw new AuthenticationException("Authentication Exception") {
            };
        } catch (Exception exception) {
            logger.error("Exception: ", exception);
            throw new UserNotExistException("Internal Server Error");
        }

        if( auth == null || !auth.isAuthenticated()) {
            throw new UserNotExistException("User Doesn't Exist22");
        }

        return new Token(jwtUtil.generateToken(user.getUsername()));
    }
}
