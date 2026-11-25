package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor @NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeDto {
		
	Long employeeId;

	@NotEmpty(message = "Employee name can't be Empty")
	@NotBlank(message = "Employee name can't be blank")
	@Size(min = 3, max=30, message = "Employee name must have at least 3 or 30 characters")
	String employeeName;
	
	@NotEmpty(message = "Employee Code can't be Empty")
	@NotBlank(message = "Employee Code can't be blank")
	@Size(min = 3, max=30, message = "Employee Code must have at least 3 or 30 characters")
	String employeeCode;
	
	@NotNull(message = "Designation ID can't be Null")
	Long designationId;
	
	String designation;
	
	@NotNull(message = "Department ID can't be Null")
	Long departmentId;
	
	String departmentName;
	
	String companyName;
}

