package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor @NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyDto {
		
	Long companyId;

	@NotEmpty(message = "Company name can't be Empty")
	@NotBlank(message = "Company name can't be blank")
	@Size(min = 3, max=30, message = "Company name must have at least 3 or 30 characters")
	String companyName;
}

