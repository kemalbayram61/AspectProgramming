package com.example.kemal.aspect;

import com.example.kemal.cache.ObjectIdentityCache;
import com.example.kemal.constant.CacheConstant;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class DataLifeCycleAspect {

    @Autowired
    private CacheManager cacheManager;

    // DataLifeCycle annotasyonu içeren fieldın getter metodunun void return type olmaması ve primitive data type olmaması kontrol ediliyor
    @Before("@annotation(com.example.kemal.annotation.DataLifeCycle)")
    public void validateMethodReturnType(JoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        if (method.getReturnType().equals(Void.TYPE)) {
            throw new IllegalArgumentException("\u001B[31m@DataLifeCycle annotasyonu void return type olan metotlarda kullanılamaz: " + method.getName() + "\u001B[0m");
        }
        if (method.getReturnType().isPrimitive()) {
            throw new IllegalArgumentException("\u001B[31@DataLifeCycle annotasyonu primitive data type return eden metotlarda kullanılamaz: " + method.getName() + "\u001B[0m");
        }
    }

    // DataLifeCycle annotasyonu içeren field nerede çağrıldığı tespit ediliyor ve bir konum bazlı doğrulama için kontrol noktası koyuluyor
    @Before("@annotation(com.example.kemal.annotation.DataLifeCycle)")
    public void beforeAnnotatedMethod(JoinPoint joinPoint) {
        StackTraceElement[] st = Thread.currentThread().getStackTrace();
        for (StackTraceElement s : st) {
            if (s.toString().contains("com.example.kemal.dao.UserDao")) {
                if (cacheManager.getCache(CacheConstant.DATA_LIFE_CYCLE_TRACE_CACHE) == null) {
                    cacheManager.getCacheNames().add(CacheConstant.DATA_LIFE_CYCLE_TRACE_CACHE);
                    System.out.println("\u001B[31mCacheConstant.DATA_LIFE_CYCLE_TRACE_CACHE oluşturuldu.\u001B[0m");
                }
                if (cacheManager.getCache(CacheConstant.DATA_LIFE_CYCLE_TRACE_CACHE) != null && cacheManager.getCache(CacheConstant.DATA_LIFE_CYCLE_TRACE_CACHE).get(s.toString()) == null) {
                    cacheManager.getCache(CacheConstant.DATA_LIFE_CYCLE_TRACE_CACHE).put(s.toString(), false);
                    System.out.println("\u001B[32m" + s + " bulgusu Spring Cachede true olarak doğrulandı.\u001B[0m");
                }
            }
        }
    }

    // DataLifeCycle annotasyonu içeren fieldın getter metodundan dönen nesne referansına yakın değer kontrol için cacheleniyor
    @AfterReturning(pointcut = "@annotation(com.example.kemal.annotation.DataLifeCycle)", returning = "result")
    public void afterReturningMethod(JoinPoint joinPoint, Object result) {
        if (ObjectIdentityCache.instance().get(result) == null) {
            ObjectIdentityCache.instance().put(result, false);
            System.out.println("\u001B[32mDönüş değeri ObjectIdentityCache ile cachelendi: " + result + "\u001B[0m");
        }
    }
}
