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
public class DataLifeCycleAspect {

    @Autowired
    private CacheManager cacheManager;

    @Before("@annotation(com.example.kemal.annotation.DataLifeCycle)")
    public void beforeAnnotatedMethod(JoinPoint joinPoint) {
        StackTraceElement[] st = Thread.currentThread().getStackTrace();
        for (StackTraceElement s : st) {
            if(s.toString().contains("com.example.kemal.dao.UserDao")) {
                if (cacheManager.getCache(CacheConstant.DATA_LIFE_CYCLE_TRACE_CACHE) == null) {
                    cacheManager.getCacheNames().add("CacheConstant.DATA_LIFE_CYCLE_TRACE_CACHE");
                    System.out.println("\u001B[31mCacheConstant.DATA_LIFE_CYCLE_TRACE_CACHE oluşturuldu.\u001B[0m");
                }
                if (cacheManager.getCache(CacheConstant.DATA_LIFE_CYCLE_TRACE_CACHE) != null && cacheManager.getCache(CacheConstant.DATA_LIFE_CYCLE_TRACE_CACHE).get(s.toString()) == null) {
                    cacheManager.getCache(CacheConstant.DATA_LIFE_CYCLE_TRACE_CACHE).put(s.toString(), true);
                    System.out.println("\u001B[32m" + s + " bulgusu Spring Cachede true olarak doğrulandı.\u001B[0m");
                }
            }
        }
    }
}
