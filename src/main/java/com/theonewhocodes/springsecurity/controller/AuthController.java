package com.theonewhocodes.springsecurity.controller;

import com.theonewhocodes.springsecurity.dto.AuthRequest;
import com.theonewhocodes.springsecurity.utils.JWTAuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTAuthUtils jwtAuthUtils;

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        if (authenticate.isAuthenticated()) {
            return jwtAuthUtils.generateToken(authRequest.getUsername());
        } else {
            return "Login failed for user: " + authRequest.getUsername();
        }
    }
}
