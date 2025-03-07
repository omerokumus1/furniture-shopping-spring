package com.example.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example")
public class FurnitureShoppingSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(FurnitureShoppingSpringApplication.class, args);
	}

}
