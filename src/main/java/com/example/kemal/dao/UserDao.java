package com.example.kemal.dao;

import com.example.kemal.caller.QueryCaller;
import com.example.kemal.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserDao {

    private final QueryCaller queryCaller;

    public UserDao(QueryCaller queryCaller) {
        this.queryCaller = queryCaller;
    }

    public void saveUser(User user, Integer age) {
        String query = "INSERT INTO users (name, age) VALUES (?, ?)";
        if (user.isActive()) {
            queryCaller.setStringParam(user.getUsername());
            queryCaller.setIntParam(age);
            queryCaller.executeUpdate(query);
            System.out.println("User saved: " + user);
        }
    }
}
