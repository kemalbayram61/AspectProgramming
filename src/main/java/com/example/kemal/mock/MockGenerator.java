package com.example.kemal.mock;

import com.example.kemal.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MockGenerator {

    @Autowired
    private final User user;
    @Autowired
    private final User user2;

    public MockGenerator(User user, User user2) {
        this.user = user;
        this.user2 = user2;
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

    public User getMockUser2() {
        this.user2.setUsername("Kemal");
        this.user2.setPassword("61");
        this.user2.setEmail("kemalbayramag@gmail.com");
        this.user2.setPhoneNumber("0661");
        this.user2.setAge(28);
        this.user2.setActive(true);
        return user2;
    }

}
