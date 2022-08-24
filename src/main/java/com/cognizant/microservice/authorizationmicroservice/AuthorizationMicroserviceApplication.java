package com.cognizant.microservice.authorizationmicroservice;

import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.cognizant.microservice.authorizationmicroservice.model.Users;
import com.cognizant.microservice.authorizationmicroservice.repository.UserRepository;

@SpringBootApplication
@EnableFeignClients
public class AuthorizationMicroserviceApplication {
	
	@Autowired
	private UserRepository repository;

	@PostConstruct
	public void initUser() {
		List<Users> users = Stream.of(new Users(101, "sathya", "sathya"), new Users(102, "admin", "admin"),new Users(102, "user", "user")

		).collect(Collectors.toList());
		repository.saveAll(users);
	}
	public static void main(String[] args) {
		SpringApplication.run(AuthorizationMicroserviceApplication.class, args);
		//final release
	}

}
