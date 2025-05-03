package com.example.kemal.mock;

import com.example.kemal.model.User;

public class MockGenerator {
    public static User getMockUser() {
        return new User("Kemal", "61", "kemalbayramag@gmail.com", "0661", true);
    }

}
