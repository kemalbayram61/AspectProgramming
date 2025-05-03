package com.example.kemal.listener;

import com.example.kemal.annotation.DataLifeCycle;
import jakarta.annotation.PostConstruct;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ApplicationListener {

    @Autowired
    private CacheManager cacheManager;

    @PostConstruct
    public void init() {
        ArrayList<String> classNames = new ArrayList<>();
        Reflections reflections = new Reflections("com.example.kemal", new SubTypesScanner(false));
        reflections.getSubTypesOf(Object.class)
                .forEach(clazz -> classNames.add(clazz.getName()));
        System.out.println("\u001B[35mProje içerisindeki sınıflar:\u001B[0m");
        classNames.forEach(clsName -> System.out.println("\u001B[36m" + clsName + "\u001B[0m"));
        classNames.forEach(this::cacheIfObjectIsDataLifeCycle);
    }

    private void cacheIfObjectIsDataLifeCycle(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            boolean hasAnnotation = false;

            if (clazz.isAnnotationPresent(DataLifeCycle.class)) {
                hasAnnotation = true;
                System.out.println("\u001B[33m" + className + " sınıfı DataLifeCycle anotasyonuna sahiptir.\u001B[0m");
            }

            for (var field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(DataLifeCycle.class)) {
                    hasAnnotation = true;
                    System.out.println("\u001B[33m" + className + " sınıfındaki " + field.getName() + " alanı DataLifeCycle anotasyonuna sahiptir.\u001B[0m");
                }
            }

            if (!hasAnnotation) {
                System.out.println("\u001B[31m" + className + " sınıfı veya alanları DataLifeCycle anotasyonuna sahip değildir.\u001B[0m");
            } else {
                if (cacheManager.getCache("classCache") == null) {
                    cacheManager.getCacheNames().add("classCache");
                    System.out.println("\u001B[31mclassCache oluşturuldu.\u001B[0m");
                }
                if (cacheManager.getCache("classCache") != null && cacheManager.getCache("classCache").get(className) == null) {
                    cacheManager.getCache("classCache").put(className, true);
                    System.out.println("\u001B[32m" + className + " sınıfı Spring Cache'e eklendi.\u001B[0m");
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("\u001B[31m" + className + " sınıfı bulunamadı.\u001B[0m");
        }
    }
}
