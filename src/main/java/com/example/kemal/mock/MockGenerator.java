package com.example.kemal.mock;

import com.example.kemal.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MockGenerator {

    @Autowired
    private final User user;

    public MockGenerator(User user) {
        this.user = user;
    }

    public User getMockUser() {
        this.user.setUsername("Kemal");
        this.user.setPassword("61");
        this.user.setEmail("kemalbayramag@gmail.com");
        this.user.setPhoneNumber("0661");
        this.user.setAge(28);
        this.user.setActive(true);
        return user;
    }

}
