package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DesignationDto {

	Long designationId;
	
	@NotBlank(message = "Designation field can't be blank")
	@Size(min = 2, max = 50, message = "Desigantion name must have at least 2 or 50 characters" )
	String designation;
}
