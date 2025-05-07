package com.example.kemal.service;

import com.example.kemal.dao.UserDao;
import com.example.kemal.mock.MockGenerator;
import com.example.kemal.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDao userDao;
    private final MockGenerator mockGenerator;

    public UserService(UserDao userDao, MockGenerator mockGenerator) {
        this.userDao = userDao;
        this.mockGenerator = mockGenerator;
    }

    public User getMockUser(){
        return this.mockGenerator.getMockUser();
    }

    public void saveUser(User user) {
        User user1 = this.mockGenerator.getMockUser2();
        userDao.saveUser(user, user1.getAge());
    }
}
