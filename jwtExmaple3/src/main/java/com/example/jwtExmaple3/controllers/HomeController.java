package com.example.jwtExmaple3.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwtExmaple3.services.UserService;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    UserService userService;
    
    @GetMapping("/user")
    public List<com.example.jwtExmaple3.models.User> getUser() {
        System.out.println("Hello users!");
        return this.userService.getUsers();
    }
}
