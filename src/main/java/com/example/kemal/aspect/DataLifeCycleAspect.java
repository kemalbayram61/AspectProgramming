package com.example.kemal.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataLifeCycleAspect {
    @Before("@annotation(com.example.kemal.annotation.DataLifeCycle)")
    public void beforeAnnotatedMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Custom annotation ile işaretlenmiş metot çağrılıyor: " + methodName);
    }
}
