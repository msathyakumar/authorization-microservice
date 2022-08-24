package com.cognizant.microservice.authorizationmicroservice.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.microservice.authorizationmicroservice.exception.ResourceNotFound;
import com.cognizant.microservice.authorizationmicroservice.model.AuthRequest;
import com.cognizant.microservice.authorizationmicroservice.service.CustomUserDetailService;
import com.cognizant.microservice.authorizationmicroservice.util.JwtUtil;
import com.google.common.net.HttpHeaders;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

	private static final Logger LOGGER1 = LoggerFactory.getLogger(AuthenticationController.class);
	
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private CustomUserDetailService userDetailService;
	@Autowired
	private AuthenticationManager authenticationManager;



//starting message 

	@GetMapping("/")
	public ResponseEntity<String> welcome() {
		LOGGER1.info("STARTED authorization microservice welcome");
		LOGGER1.info("END - authorization microservice welcome");
		return ResponseEntity.ok("Wecome to security application");
	}

//jwt token authentication using user name and password

	@PostMapping("/authenticate")
	public ResponseEntity<String> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		LOGGER1.info("STARTED - generateToken");
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));

		} catch (Exception e) {
			LOGGER1.error("EXCEPTION - generateToken");
			throw new ResourceNotFound("user not found");
		}
		Map<Object, Object> model = new HashMap<>();
        model.put("username", authRequest.getUserName());
        model.put("token",jwtUtil.generateToken(authRequest.getUserName()) );
        String token = jwtUtil.generateToken(authRequest.getUserName());
       
		LOGGER1.info("END - generateToken");
//		return ResponseEntity.ok()
//        .header(
//            HttpHeaders.AUTHORIZATION,jwtUtil.generateToken(authRequest.getUserName())
		return new ResponseEntity<String>(token,HttpStatus.OK);
//            
//        )
		//return ResponseEntity.ok(model);
		//return ResponseEntity.ok().header(jwtUtil.generateToken(authRequest.getUserName())).body(jwtUtil.generateToken(authRequest.getUserName()));

		//return ResponseEntity.ok().header("token",jwtUtil.generateToken(authRequest.getUserName())).body(jwtUtil.generateToken(authRequest.getUserName()));
	}

//validtiion of the generated jwt token to access '/authorize' endpoint

	@GetMapping("/authorize")
	public ResponseEntity<?> authorization(@RequestHeader("Authorization") String token1) {

		LOGGER1.info("STARTED - authorization");
		String token = token1.substring(7);

		UserDetails user = userDetailService.loadUserByUsername(jwtUtil.extractUsername(token));

		if (jwtUtil.validateToken(token, user)) {
			LOGGER1.info("END - authorization");
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else {
			LOGGER1.info("END - authorization");
			return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
		}
	}
}