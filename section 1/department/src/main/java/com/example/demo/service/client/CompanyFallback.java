package com.example.demo.service.client;

import org.springframework.cloud.client.circuitbreaker.httpservice.HttpServiceFallback;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.GetExchange;

import com.example.demo.dto.CompanyDto;

@Component
@HttpServiceFallback(value = CompanyFallback.class, group = "company")
public class CompanyFallback {

	@GetExchange("/{id}")
	public ResponseEntity<CompanyDto> getCompanyById(@RequestHeader("trainingtracker-correlation-id") String correlationId, @PathVariable Long id){
		
		return null;
	}
	
}
