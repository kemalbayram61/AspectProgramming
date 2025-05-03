package com.example.kemal.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* com.example.kemal.controller..*(..))")
    public void logBeforeControllerMethods() {
        System.out.println("\u001B[34mControllerda metot çağrılacak.\u001B[0m");
    }

    @After("execution(* com.example.kemal.controller..*(..))")
    public void logAfterControllerMethods() {
        System.out.println("\u001B[32mControllerda metot çağrıldı.\u001B[0m");
    }


}
