package com.example.jwtExmaple3.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwtExmaple3.helpers.JwtHelper;
import com.example.jwtExmaple3.models.JwtRequest;
import com.example.jwtExmaple3.models.JwtResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    // privide userDetails
    @Autowired
    private UserDetailsService userDetailsService;

    // To create Jwt JwtToken
    @Autowired
    private JwtHelper jwtHelper;

    // To authenticate
    @Autowired
    private AuthenticationManager authenticationManager;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);



    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        this.doAuthenticate(request.getEmail(), request.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.jwtHelper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
        .jwtToken(token).username(userDetails.getUsername()).build();

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            authenticationManager.authenticate(authentication);
        } 
        catch (Exception e) {
            throw new BadCredentialsException("Invalid Username or passwor !!");

        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHadler() {

        return "Credentails Invalid !!";
    }
    
}
