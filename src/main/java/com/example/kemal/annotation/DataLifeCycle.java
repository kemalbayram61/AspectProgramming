package com.example.kemal.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Bu annotasyon sadece getter metotlara verilmelidir.
 * Bu annotasyonun verildiği getter metot primitive data type return edemez
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataLifeCycle {
    String[] allowedDBs() default {};
    String[] allowedTables() default {};
}
