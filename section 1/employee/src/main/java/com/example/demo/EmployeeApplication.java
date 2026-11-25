package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.service.registry.ImportHttpServices;

import com.example.demo.clients.DepartmentClient;
import com.example.demo.clients.DesignationClient;
import com.example.demo.dto.EmployeeContactInfoDto;

@SpringBootApplication
@ImportHttpServices(group = "designation", types = DesignationClient.class)
@ImportHttpServices(group = "department", types = DepartmentClient.class)
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = EmployeeContactInfoDto.class)
public class EmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}

}
