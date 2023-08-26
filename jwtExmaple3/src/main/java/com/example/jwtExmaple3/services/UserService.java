package com.example.jwtExmaple3.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.jwtExmaple3.models.User;

@Service
public class UserService {
    
    List<User> userList = new ArrayList<User>();
    
    public UserService() {
        userList.add(new User(UUID.randomUUID().toString(),"Akash Kumar", "araushan11@gmail.com"));
        userList.add(new User(UUID.randomUUID().toString(),"Suraj Kumar", "suraj05@gmail.com"));
        userList.add(new User(UUID.randomUUID().toString(),"Rahul Kumar", "rahul505@gmail.com"));
    }

    public List<User> getUsers() {
        return this.userList;
    }
}
