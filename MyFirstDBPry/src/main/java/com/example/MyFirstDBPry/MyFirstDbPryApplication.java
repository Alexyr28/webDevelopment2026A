package com.example.MyFirstDBPry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class MyFirstDbPryApplication {
	@RequestMapping("/")
	String home(){
		return "Hello World! Spring Boot 4 is here Alexander Yamá Rosero desde Visual";
	}
	public static void main(String[] args) {
		SpringApplication.run(MyFirstDbPryApplication.class, args);
	}

}
