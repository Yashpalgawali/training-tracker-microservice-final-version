package com.example.demo.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import com.example.demo.dto.Frequency;

@HttpExchange(value = "/api")
public interface FrequencyClient {

	@GetExchange("/{id}")
	public ResponseEntity<Frequency> getFrequencyById(@PathVariable Long id);
}
