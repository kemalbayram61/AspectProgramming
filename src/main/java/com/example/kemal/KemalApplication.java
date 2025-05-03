package com.example.kemal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class KemalApplication {

	public static void main(String[] args) {
		SpringApplication.run(KemalApplication.class, args);
	}

}
