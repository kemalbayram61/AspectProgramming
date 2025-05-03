package com.example.kemal.aspect;

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

    @Before("execution(* com.example.kemal.caller.QueryCaller.executeUpdate(..))")
    public void beforeUpdateQuery() {
        if (cacheManager.getCache(CacheConstant.DATA_LIFE_CYCLE_TRACE_CACHE) != null) {
            System.out.println("\u001B[32mCache doğrulanmış alan kontrolü.\u001B[0m");
        }
    }

    @Before("execution(* com.example.kemal.caller.QueryCaller.setStringParam(..))")
    public void beforeSetStringParam(JoinPoint joinPoint) {
        StackTraceElement[] st = Thread.currentThread().getStackTrace();
        for (StackTraceElement s : st) {
            if (s.toString().contains("com.example.kemal.dao.UserDao")) {
                if (cacheManager.getCache(CacheConstant.DATA_LIFE_CYCLE_TRACE_CACHE) != null && cacheManager.getCache(CacheConstant.DATA_LIFE_CYCLE_TRACE_CACHE).get(s.toString()) == null) {
                    cacheManager.getCache(CacheConstant.DATA_LIFE_CYCLE_TRACE_CACHE).put(s.toString(), false);
                    System.out.println("\u001B[32m" + s + " bulgusu Spring Cache'e doğrulanmamış olarak eklendi.\u001B[0m");
                }
            }
        }
    }

    @Before("execution(* com.example.kemal.caller.QueryCaller.setIntParam(..))")
    public void beforeSetIntParam(JoinPoint joinPoint) {
        StackTraceElement[] st = Thread.currentThread().getStackTrace();
        for (StackTraceElement s : st) {
            if (s.toString().contains("com.example.kemal.dao.UserDao")) {
                if (cacheManager.getCache(CacheConstant.DATA_LIFE_CYCLE_TRACE_CACHE) != null && cacheManager.getCache(CacheConstant.DATA_LIFE_CYCLE_TRACE_CACHE).get(s.toString()) == null) {
                    cacheManager.getCache(CacheConstant.DATA_LIFE_CYCLE_TRACE_CACHE).put(s.toString(), false);
                    System.out.println("\u001B[32m" + s + " bulgusu Spring Cache'e doğrulanmamış olarak eklendi.\u001B[0m");
                }
            }
        }
    }
}
