package com.example.kemal.aspect;

import com.example.kemal.cache.ObjectIdentityCache;
import com.example.kemal.constant.CacheConstant;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class QueryCallerAspect {

    @Autowired
    private CacheManager cacheManager;

    // Sorgu çalıştırılmadan önce DataLifeCycle annotasyonlu field kullanılmış mı teyit eiliyor
    @Before("execution(* com.example.kemal.caller.QueryCaller.executeUpdate(..))")
    public void beforeUpdateQuery() {
        if (ObjectIdentityCache.instance().isAnyTrue())
            throw new RuntimeException("\u001B[31mDataLifeCycle annotasyonu içeren bir field insert edilmeye çalışılıyor\u001B[0m");
        if (cacheManager.getCache(CacheConstant.DATA_LIFE_CYCLE_TRACE_CACHE) != null)
            if (cacheManager.getCache(CacheConstant.DATA_LIFE_CYCLE_TRACE_CACHE).getNativeCache().toString().contains("true"))
                throw new RuntimeException("\u001B[31mDataLifeCycle annotasyonu istenilmeyen bir şekilde insert edilmeye çalışılıyor.\u001B[0m");
    }

    @Before("execution(* com.example.kemal.caller.QueryCaller.setStringParam(..))")
    public void beforeSetStringParam(JoinPoint joinPoint) {
        // Field referansına yakın bir değer ile doğrulama yapılıyor
        Object param = joinPoint.getArgs()[0];
        if (ObjectIdentityCache.instance().get(param) != null) {
            ObjectIdentityCache.instance().put(param, true);
        }

        // StackTraceElement ile çağrım yapılan konum üzerinden doğrulama yapılıyor
        StackTraceElement[] st = Thread.currentThread().getStackTrace();
        for (StackTraceElement s : st) {
            if (s.toString().contains("com.example.kemal.dao.UserDao")) {
                if (cacheManager.getCache(CacheConstant.DATA_LIFE_CYCLE_TRACE_CACHE) != null && cacheManager.getCache(CacheConstant.DATA_LIFE_CYCLE_TRACE_CACHE).get(s.toString()) != null) {
                    cacheManager.getCache(CacheConstant.DATA_LIFE_CYCLE_TRACE_CACHE).put(s.toString(), true);
                    System.out.println("\u001B[32m" + s + " bulgusu Spring Cache'e doğrulanmamış olarak eklendi.\u001B[0m");
                }
            }
        }
    }

    @Before("execution(* com.example.kemal.caller.QueryCaller.setIntParam(..))")
    public void beforeSetIntParam(JoinPoint joinPoint) {
        // Field referansına yakın bir değer ile doğrulama yapılıyor
        Object param = joinPoint.getArgs()[0];
        if (ObjectIdentityCache.instance().get(param) != null) {
            ObjectIdentityCache.instance().put(param, true);
        }

        // StackTraceElement ile çağrım yapılan konum üzerinden doğrulama yapılıyor
        StackTraceElement[] st = Thread.currentThread().getStackTrace();
        for (StackTraceElement s : st) {
            if (s.toString().contains("com.example.kemal.dao.UserDao")) {
                if (cacheManager.getCache(CacheConstant.DATA_LIFE_CYCLE_TRACE_CACHE) != null && cacheManager.getCache(CacheConstant.DATA_LIFE_CYCLE_TRACE_CACHE).get(s.toString()) != null) {
                    cacheManager.getCache(CacheConstant.DATA_LIFE_CYCLE_TRACE_CACHE).put(s.toString(), true);
                    System.out.println("\u001B[32m" + s + " bulgusu Spring Cache'e doğrulanmamış olarak eklendi.\u001B[0m");
                }
            }
        }
    }
}
