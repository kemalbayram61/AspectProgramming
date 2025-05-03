package com.example.kemal.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class QueryCallerAspect {
    @Before("execution(* com.example.kemal.caller.QueryCaller.executeUpdate(..))")
    public void beforeUpdateQuery() {
        System.out.println("Before executing update query");
    }
}
