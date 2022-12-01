package com.infosys.infytel.customer.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.infosys.infytel.customer.dto.PlanDTO;

// Specify for which microservice feign is created, and the url of API Gateway
@FeignClient(name="PlanMS", url="http://localhost:9000/")
public interface PlanFeign {
	
	@GetMapping(value="/plans/{planId}")
	PlanDTO getSpecificPlan(@PathVariable("planId") int planId) ;
}
