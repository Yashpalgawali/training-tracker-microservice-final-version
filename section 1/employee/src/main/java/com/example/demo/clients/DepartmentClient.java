package com.example.demo.clients;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import com.example.demo.dto.DepartmentDto;

@HttpExchange(value = "/api")
public interface DepartmentClient {

	@GetExchange("/{id}")
	public ResponseEntity<DepartmentDto> getDepartmentById(
			@RequestHeader("trainingtracker-correlation-id") String correlationId, @PathVariable Long id);

}
