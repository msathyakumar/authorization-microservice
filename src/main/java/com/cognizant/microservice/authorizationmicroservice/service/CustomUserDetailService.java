package com.cognizant.microservice.authorizationmicroservice.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cognizant.microservice.authorizationmicroservice.exception.ResourceNotFound;
import com.cognizant.microservice.authorizationmicroservice.model.Users;
import com.cognizant.microservice.authorizationmicroservice.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailService.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		try {
			LOGGER.info("STARTED - loadUserByUsername");

			Users user = userRepository.findByUserName(username);

			LOGGER.info("ENDED - loadUserByUsername");
			return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
					new ArrayList<>());
		} catch (Exception e) {
			LOGGER.info("ERROR - User Not Found");

			throw new ResourceNotFound("User with Username Not Found");

		}
	}

}
