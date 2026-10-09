package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -377112223956862886L;

	public ResourceNotFoundException(String resource, String fieldName, String fieldValue) {
//		super(String.format("Resource %d is not found for field %d with value %d ", resourceName,resourceField,resourceValue));
		super(String.format("%s is not found with value %s : %s",resource,fieldName,fieldValue ));
	}
	
}
