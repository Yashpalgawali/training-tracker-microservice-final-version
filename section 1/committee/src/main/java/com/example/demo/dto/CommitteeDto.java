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
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommitteeDto {

	Long committeeId;

	@NotEmpty(message = "Committee name can't be Empty")
	@NotBlank(message = "Committee name can't be blank")
	@Size(min = 3, max = 30, message = "Committee name must have at least 3 or 30 characters")
	String committeeName;
}
