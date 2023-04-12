package com.project.lumos.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/* 추가한 starter
 * jdbc api
 * oracle driver
 * spring boot devtools
 * spring web
 * spring security
 * spring data jpa
 * */
@SpringBootApplication
@ComponentScan(basePackages = {"com.project.lumos"})
public class LumosApplication {

	public static void main(String[] args) {
		SpringApplication.run(LumosApplication.class, args);
	}

}