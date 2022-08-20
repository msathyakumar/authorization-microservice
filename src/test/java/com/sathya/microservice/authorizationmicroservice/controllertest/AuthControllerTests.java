package com.sathya.microservice.authorizationmicroservice.controllertest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.http.MediaType;

import com.cognizant.microservice.authorizationmicroservice.controller.AuthenticationController;
import com.cognizant.microservice.authorizationmicroservice.model.AuthRequest;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest(classes = AuthenticationController.class)
@AutoConfigureMockMvc
public class AuthControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private AuthenticationController authorizationController;

	@Test
	public void contextLoads() {
		assertNotNull(authorizationController);
	}

	@Test
	public void loginTestSuccess() throws Exception {
		AuthRequest admin = new AuthRequest("Iftak", "password1");

		ResultActions actions = mockMvc
				.perform(post("/authenticate").contentType(MediaType.APPLICATION_JSON).content(asJsonString(admin)));
		actions.andExpect(status().isOk());
	}

	@Test
	public void loginTestFail() throws Exception {
		AuthRequest admin = new AuthRequest("WrongUser", "password1");

		ResultActions actions = mockMvc
				.perform(post("/authenticate").contentType(MediaType.APPLICATION_JSON).content(asJsonString(admin)));
		actions.andExpect(status().isNotFound());
	}

	public static String asJsonString(AuthRequest admin) {
		try {
			return new ObjectMapper().writeValueAsString(admin);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}