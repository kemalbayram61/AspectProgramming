package com.example.kemal.controller;

import com.example.kemal.model.User;
import com.example.kemal.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    private final UserService userService;

    public TestController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    @GetMapping("/user")
    public User getUser(){
        return this.userService.getMockUser();
    }

}
