package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.example.demo.dto.CommitteeContactInfoDto;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = CommitteeContactInfoDto.class)
public class CommitteeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommitteeApplication.class, args);
	}

}
