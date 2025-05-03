package com.example.kemal.model;

import com.example.kemal.annotation.DataLifeCycle;

public class User {
    @DataLifeCycle(
            allowedDBs = {"ORACLE"},
            allowedTables = {"USER"}
    )
    private String username;
    @DataLifeCycle()
    private String password;
    private String email;
    private String phoneNumber;
    private boolean isActive;

    public User(String username, String password, String email, String phoneNumber, boolean isActive) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
    }

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
}
