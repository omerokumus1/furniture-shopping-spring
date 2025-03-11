package com.omerokumus.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.omerokumus")
@ComponentScan(basePackages = "com.omerokumus")
@EnableJpaRepositories(basePackages = "com.omerokumus")
public class FurnitureShoppingSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(FurnitureShoppingSpringApplication.class, args);
	}

}
