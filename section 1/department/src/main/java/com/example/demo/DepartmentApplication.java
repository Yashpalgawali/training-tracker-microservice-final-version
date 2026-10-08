package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.example.demo.dto.DepartmentContactInfo;

@SpringBootApplication
@EnableConfigurationProperties(value = DepartmentContactInfo.class)
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class DepartmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepartmentApplication.class, args);
	}

}
