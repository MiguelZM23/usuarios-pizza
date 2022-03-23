package com.capgemini;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;

@EnableOpenApi
@SpringBootApplication
public class Application implements CommandLineRunner { 

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Docket api() {                
   	    return new Docket(DocumentationType.OAS_30)          
	      .select()
	      .apis(RequestHandlerSelectors.basePackage("com.capgemini.application.resource"))
	      .paths(PathSelectors.ant("/**"))
	      .build()
	      .apiInfo(new ApiInfoBuilder()
	                .title("API Usuarios proyecto Pizzeria")
	                .description("Microservicio API REST de usuarios de la pizzeria")
	                .version("1.0")
	                .license("Apache License Version 2.0")
	                .contact(new Contact("Admin", "http://www.pizzeriasxpress.com", "myeaddress@example.com"))
	                .build());
	}
	

	@Override
	@Transactional
	public void run(String... args) {

	}
}
