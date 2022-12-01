package com.infosys.infytel.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.infytel.customer.controller.FriendFeign;
import com.infosys.infytel.customer.controller.PlanFeign;
import com.infosys.infytel.customer.dto.PlanDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.vavr.concurrent.Future;

@Service
public class CustomerCircuitBreakerService {	
//	@Autowired
//	private RestTemplate restTemplate;
	
	@Autowired
	private FriendFeign friendFeign;
	
	@Autowired
	private PlanFeign planFeign;
	
	@CircuitBreaker(name="customerService")
	public Future<PlanDTO> getSpecificPlan(Integer planId) {
//		return Future.of(()->restTemplate.getForObject("http://PlanMS/plans/"+planId, PlanDTO.class));
		return Future.of(()->planFeign.getSpecificPlan(planId));
	}
	
	@CircuitBreaker(name="customerService")
	public Future<List<Long>> getSpecificFriends(Long phoneNo){
		return Future.of(()->friendFeign.getSpecificFriends(phoneNo));
//		return Future.of(()->restTemplate.getForObject("http://FriendMS/customers/"+phoneNo+"/friends", List.class));
	}
}
