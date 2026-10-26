package com.example.gatewayserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class FallBackController {

	@GetMapping("/companyContactSupport")
	public Mono<String> companyContactSupport(){
		return Mono.just("An error occurre. Please try after some time or contact the support team");
	}
	
	@GetMapping("/departmentContactSupport")
	public Mono<String> departmentContactSupport(){
		return Mono.just("An error occurre. Please try after some time or contact the Department support team");
	}
}
