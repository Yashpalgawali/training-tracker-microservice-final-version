package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.EmployeeDto;

public interface IEmployeeService {

	public void createEmployee(EmployeeDto empDto);
	
	public EmployeeDto getEmployeeById(String correlationId, Long id);
	
	public EmployeeDto getEmployeeByEmployeeCode(String correlationId,String code);
	
	public EmployeeDto getEmployeeByName(String correlationId,String name);
	
	public List<EmployeeDto> getAllEmployees(String correlationId);
	
	public void updateEmployee(EmployeeDto empDto);

}
