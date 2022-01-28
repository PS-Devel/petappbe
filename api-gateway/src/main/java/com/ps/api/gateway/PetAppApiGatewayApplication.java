package com.ps.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PetAppApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetAppApiGatewayApplication.class, args);
	}
}
