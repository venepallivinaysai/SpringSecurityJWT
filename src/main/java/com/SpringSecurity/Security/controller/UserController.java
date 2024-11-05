package com.SpringSecurity.Security.controller;

import com.SpringSecurity.Security.entity.User;
import com.SpringSecurity.Security.exception.CustomizedException;
import com.SpringSecurity.Security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user){
        return userService.registerUser(user);
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") Integer userId) throws CustomizedException {
        return userService.deleteUser(userId);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) throws CustomizedException {
        System.out.println("User data : " + user);
        return userService.verify(user);
    }
}
