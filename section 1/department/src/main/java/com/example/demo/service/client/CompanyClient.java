package com.example.demo.service.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import com.example.demo.dto.CompanyDto;

@HttpExchange("/api")
public interface CompanyClient {

	@GetExchange("/{id}")
	public ResponseEntity<CompanyDto> getCompanyById(@RequestHeader("trainingtracker-correlation-id") String correlationId, @PathVariable Long id);
	
}
