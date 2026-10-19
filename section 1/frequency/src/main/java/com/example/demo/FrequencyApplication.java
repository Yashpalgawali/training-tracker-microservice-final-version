package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.example.demo.dto.FrequencyContactInfoDto;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = FrequencyContactInfoDto.class)
public class FrequencyApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrequencyApplication.class, args);
	}
}
