package com.sathya.microservice.authorizationmicroservice.model.test;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.meanbean.test.BeanTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.microservice.authorizationmicroservice.model.Users;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthorizationModelTest {
	
	@Test
	void testPensionerBean() {		
		final BeanTester beanTester = new BeanTester();
		beanTester.getFactoryCollection();
		beanTester.testBean(Users.class);
	}

}
