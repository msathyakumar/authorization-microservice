package com.cognizant.microservice.authorizationmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.microservice.authorizationmicroservice.model.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {

	Users findByUserName(String username);

}
