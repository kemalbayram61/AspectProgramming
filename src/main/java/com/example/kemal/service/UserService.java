package com.example.kemal.service;

import com.example.kemal.mock.MockGenerator;
import com.example.kemal.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public User getMockUser(){
        return MockGenerator.getMockUser();
    }
}
