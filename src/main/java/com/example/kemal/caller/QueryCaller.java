package com.example.kemal.caller;

import org.springframework.stereotype.Component;

@Component
public class QueryCaller {
    public void executeUpdate(String query) {
        System.out.println("Executing query: " + query);
    }

    public void setStringParam(String param) {
        System.out.println("Setting string parameter: " + param);
    }

    public void setIntParam(int param) {
        System.out.println("Setting integer parameter: " + param);
    }
}
