package com.ahci.userservice.controller;

import com.ahci.userservice.model.User;
import com.ahci.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Long id){
        return userService.findById(id).orElse(new User());
    }

    @PostMapping
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping
    public List<User> showAllUsers(){
        return userService.getAll();
    }
}
