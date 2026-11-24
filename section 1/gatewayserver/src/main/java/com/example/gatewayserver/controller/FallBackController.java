package com.example.gatewayserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class FallBackController {

	private final Logger logger = LoggerFactory.getLogger(FallBackController.class);

	public FallBackController() {
		super();
	}

	@GetMapping("/companyContactSupport")
	public Mono<String> companyContactSupport() {
		logger.warn("circuitebreakerfallback is triggered");
		return Mono.just("An error occurred. Please try after some time or contact the support team");
	}
	
	@GetMapping("/departmentContactSupport")
	public Mono<String> departmentContactSupport() {
		return Mono.just("An error occurred. Please try after some time or contact the Department support team");
	}
}
