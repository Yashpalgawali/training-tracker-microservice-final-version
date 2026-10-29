package com.example.demo.client;

import org.springframework.cloud.client.circuitbreaker.httpservice.HttpServiceFallback;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.Frequency;

@Component
@HttpServiceFallback(group = "frequency", value = FrequencyFallBack.class)
public class FrequencyFallBack {

	@GetMapping("/{id}")
	public ResponseEntity<Frequency> getFrequencyById(@PathVariable Long id) {
		return null;
	}
}
