package com.example.demo.config;

import java.time.LocalDateTime;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TrainingTrackerRouteConfig {

	@Bean
	RouteLocator trainingTrackerRouteConfiguration(RouteLocatorBuilder routeLocatorBuilder) {
		
		return routeLocatorBuilder.routes()
				.route(p ->  p.path("/trainingtracker/company/**")
						.filters(f -> f.rewritePath("/trainingtracker/company/(?<segment>.*)", "/${segment}")
								 	.addResponseHeader("X-RESPONSE-HEADER", LocalDateTime.now().toString())
								)
						.uri("lb://COMPANY")
				)
				
				.route(p ->  p.path("/trainingtracker/department/**")
						.filters(f -> f.rewritePath("/trainingtracker/department/(?<segment>.*)", "/${segment}")
									  .addResponseHeader("X-RESPONSE-HEADER", LocalDateTime.now().toString())
									  
								)
						.uri("lb://DEPARTMENT")
				)
				.route(p ->  p.path("/trainingtracker/designation/")
						.filters(f -> f.rewritePath("/trainingtracker/designation/(?<segment>.*)", "/${segment}")
											.addResponseHeader("X-RESPONSE-HEADER", LocalDateTime.now().toString())
								)
						.uri("lb://DESIGNATION")
				)
				.route(p ->  p.path("/trainingtracker/frequency/")
						.filters(f -> f.rewritePath("/trainingtracker/frequency/(?<segment>.*)", "/${segment}")
										.addResponseHeader("X-RESPONSE-HEADER", LocalDateTime.now().toString())
								)
						.uri("lb://FREQUENCY")
				)
				.route(p ->  p.path("/trainingtracker/committee/")
						.filters(f -> f.rewritePath("/trainingtracker/committee/(?<segment>.*)", "/${segment}")
										.addResponseHeader("X-RESPONSE-HEADER", LocalDateTime.now().toString())
								)
						.uri("lb://COMMITTEE")
				)
				.build();
	}
}
