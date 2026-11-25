package com.example.demo.clients;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import com.example.demo.dto.DesignationDto;

@HttpExchange(value = "/api")
public interface DesignationClient {

	@GetExchange("/{id}")
	public ResponseEntity<DesignationDto> getDesignationById(@PathVariable Long id);
}
