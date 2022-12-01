package com.infosys.infytel.customer.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.infosys.infytel.customer.dto.CustomerDTO;
import com.infosys.infytel.customer.dto.LoginDTO;
import com.infosys.infytel.customer.dto.PlanDTO;
import com.infosys.infytel.customer.service.CustomerCircuitBreakerService;
import com.infosys.infytel.customer.service.CustomerService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@CrossOrigin
@RefreshScope
public class CustomerController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CustomerService custService;
	
	@Autowired
	private CustomerCircuitBreakerService custCircuitService;
	
	// Create a new customer
	@RequestMapping(value = "/customers", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createCustomer(@RequestBody CustomerDTO custDTO) {
		logger.info("Creation request for customer {}", custDTO);
		custService.createCustomer(custDTO);
	}

	// Login
	@RequestMapping(value = "/login", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean login(@RequestBody LoginDTO loginDTO) {
		logger.info("Login request for customer {} with password {}", loginDTO.getPhoneNo(),loginDTO.getPassword());
		return custService.login(loginDTO);
	}

	// Fetches full profile of a specific customer
	@RequestMapping(value = "/customers/{phoneNo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomerDTO getCustomerProfile(@PathVariable Long phoneNo) {
		
		logger.info("Profile request for customer {}", phoneNo);
		long overallStart=System.currentTimeMillis();
		
		CustomerDTO custDTO=custService.getCustomerProfile(phoneNo);
		long planStart=System.currentTimeMillis();
		
		PlanDTO planDTO=custCircuitService.getSpecificPlan(custDTO.getCurrentPlan().getPlanId());
		long planEnd=System.currentTimeMillis();
		
		long friendStart=System.currentTimeMillis();
		List<Long> friends=custCircuitService.getSpecificFriends(phoneNo);
		
		long friendEnd=System.currentTimeMillis();
		long overallEnd=System.currentTimeMillis();
		
		custDTO.setCurrentPlan(planDTO);
		custDTO.setFriendAndFamily(friends);
		logger.info("Total overall Time Taken for the Request {}", overallEnd-overallStart);
		logger.info("Total Time Taken for the Plan Request {}", planEnd-planStart);
		logger.info("Total Time Taken for the Friend Request {}", friendEnd-friendStart);
		
		return custDTO;
	}


}
