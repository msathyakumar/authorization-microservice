package com.cognizant.microservice.authorizationmicroservice.model;

import javax.persistence.Entity;

import javax.persistence.Id;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Users {

	@Id
	private int id;
	private String userName;
	private String password;
	

}
