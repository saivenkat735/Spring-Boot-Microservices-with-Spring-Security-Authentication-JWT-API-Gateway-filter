package com.ust.AuthenticationService.controller;

import com.ust.AuthenticationService.entity.UserCredentialsEntity;

import com.ust.AuthenticationService.service.UserCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")

public class UserCredentialsController {
    @Autowired
    private UserCredentialsService userCredentialsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public UserCredentialsEntity register(@RequestBody UserCredentialsEntity user) {
        return userCredentialsService.register(user);
    }
    @GetMapping("/validate/token")
    public boolean validateToken(@RequestParam String token) {
        return userCredentialsService.verifyToken(token);
    }

    @PostMapping("/validate/user")
    public String getToken(@RequestBody UserCredentialsEntity user) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));
        if(authenticate.isAuthenticated()){
//            return "Success" ;
            return userCredentialsService.generateToken(user.getName());
        }
        return null;
    }

}
