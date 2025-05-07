package com.example.kemal.model;

import com.example.kemal.annotation.DataLifeCycle;
import org.springframework.stereotype.Component;

@Component
public class User {
    private String username;
    private String password;
    private Integer age;
    private String email;
    private String phoneNumber;
    private boolean isActive;

    @DataLifeCycle(
            allowedDBs = {"ORACLE"},
            allowedTables = {"USER"}
    )
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @DataLifeCycle(
            allowedDBs = {"ORACLE"},
            allowedTables = {"USER"}
    )
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @DataLifeCycle(
            allowedDBs = {"ORACLE"},
            allowedTables = {"USER"}
    )
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
