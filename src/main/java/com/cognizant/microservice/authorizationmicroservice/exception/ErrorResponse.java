package com.cognizant.microservice.authorizationmicroservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
	private String message;
	private int statusCode;
	private Long exceptionTime;
}
