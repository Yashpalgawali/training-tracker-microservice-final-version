package com.example.gatewayserver;

import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	@Bean
	RouteLocator trainingTrackerRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {

		return routeLocatorBuilder.routes()
				.route(p -> p.path("/trainingtracker/company/**")
						.filters(f -> f.rewritePath("/trainingtracker/company/(?<segment>.*)", "/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
								.circuitBreaker(config-> 
													config.setName("companyCircuitBreaker")
															.setFallbackUri("forward:/companyContactSupport")
											 	)
						).uri("lb://COMPANY"))
				.route(p -> p.path("/trainingtracker/department/**")
						.filters(f -> f.rewritePath("/trainingtracker/department/(?<segment>.*)", "/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
//								.circuitBreaker(config -> 
//													config.setName("departmentCircuitBreaker")
//										)
								
						).uri("lb://DEPARTMENT")
				)
				.route(p -> p.path("/trainingtracker/designation/**")
						.filters(f -> f.rewritePath("/trainingtracker/designation/(?<segment>.*)", "/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
						).uri("lb://DESIGNATION")
				)
				.route(p -> p.path("/trainingtracker/frequency/**")
						.filters(f -> f.rewritePath("/trainingtracker/frequency/(?<segment>.*)", "/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
						).uri("lb://FREQUENCY")
				)
				.route(p -> p.path("/trainingtracker/committee/**")
						.filters(f -> f.rewritePath("/trainingtracker/committee/(?<segment>.*)", "/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
						).uri("lb://COMMITTEE"))
				.route(p -> p.path("/trainingtracker/training/**")
						.filters(f -> f.rewritePath("/trainingtracker/training/(?<segment>.*)", "/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
						).uri("lb://TRAINING"))
				.build();
	}

}
