package com.infosys.infytel.customer.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.infosys.infytel.customer.dto.PlanDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class CustomerCircuitBreakerService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RestTemplate restTemplate;
	
	@CircuitBreaker(name="customerService", fallbackMethod="getPlanProfileFallback")
	public PlanDTO getSpecificPlan(Integer planId) {
		return restTemplate.getForObject("http://PlanMS/plans/"+planId, PlanDTO.class);
	}
	
	@SuppressWarnings("unchecked")
	@CircuitBreaker(name="customerService", fallbackMethod="getFriendsProfileFallback")
	public List<Long> getSpecificFriends(Long phoneNo){
		return restTemplate.getForObject("http://FriendMS/customers/"+phoneNo+"/friends", List.class);
	}
	
	public PlanDTO getPlanProfileFallback(Integer planId, Throwable throwable) {
		logger.info("============ In Fallback ================");
		return new PlanDTO();
	}
	
	public List<Long> getFriendsProfileFallback(Long phoneNo, Throwable throwable){
		logger.info("============ In Fallback ================");
		return null;
	}
}
