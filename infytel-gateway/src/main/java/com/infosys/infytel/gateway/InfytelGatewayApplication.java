package com.infosys.infytel.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class InfytelGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfytelGatewayApplication.class, args);
	}

}
