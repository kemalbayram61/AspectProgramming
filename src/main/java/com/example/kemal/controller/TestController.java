package com.example.kemal.controller;

import com.example.kemal.mock.MockGenerator;
import com.example.kemal.model.User;
import com.example.kemal.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    private final UserService userService;
    private final MockGenerator mockGenerator;

    public TestController(UserService userService, MockGenerator mockGenerator) {
        this.userService = userService;
        this.mockGenerator = mockGenerator;
    }

    @RequestMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    @GetMapping("/user")
    public User getUser(){
        return this.userService.getMockUser();
    }

    @PostMapping("/user")
    public void createUser(){
        User user = this.mockGenerator.getMockUser();
        this.userService.saveUser(user);
    }

}
