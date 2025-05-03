package com.example.kemal.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface DataLifeCycle {
    String[] allowedDBs() default {};
    String[] allowedTables() default {};
}
