package com.ballistic.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.ballistic.rest")
public class RestServiceRestTeamplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestServiceRestTeamplateApplication.class, args);
	}
}
