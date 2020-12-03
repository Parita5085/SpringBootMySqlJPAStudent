package com.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan({"com.spring.model", "com.spring.controller", "com.spring.repo"})
public class SpringBootMySqlJpaStudentApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMySqlJpaStudentApplication.class, args);
	}
}    
