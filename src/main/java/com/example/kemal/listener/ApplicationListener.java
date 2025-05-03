package com.example.kemal.listener;

import com.example.kemal.annotation.DataLifeCycle;
import jakarta.annotation.PostConstruct;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ApplicationListener {
    @PostConstruct
    public void init() {
        ArrayList<String> classNames = new ArrayList<>();
        System.out.println("\u001B[35mProje içerisindeki sınıflar:\u001B[0m");
        Reflections reflections = new Reflections("com.example.kemal", new SubTypesScanner(false));
        reflections.getSubTypesOf(Object.class)
                .forEach(clazz -> classNames.add(clazz.getName()));
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
            }
        } catch (ClassNotFoundException e) {
            System.out.println("\u001B[31m" + className + " sınıfı bulunamadı.\u001B[0m");
        }
    }
}
