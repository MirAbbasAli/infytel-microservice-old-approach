package com.infosys.infytel.customer.controller;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="FriendMS",url="http://localhost:9000/")
public interface FriendFeign {
	
	@GetMapping("/customers/{phoneNo}/friends")
	List<Long> getSpecificFriends(@PathVariable("phoneNo")Long phoneNo);
}
