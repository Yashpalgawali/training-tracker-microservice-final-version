package com.example.demo.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ConfigurationProperties(prefix = "department")
@AllArgsConstructor @NoArgsConstructor
public class DepartmentContactInfo {

	private String message;
	
	private Map<String , String> contactDetails;
	
	private List<String> onCallSupport;
}
