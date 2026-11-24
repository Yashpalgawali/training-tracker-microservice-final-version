package com.example.demo.mapper;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.entity.Employee;

public class EmployeeMapper {

	public static Employee mapToEmployee(EmployeeDto employeeDto, Employee employee) {

		employee.setEmployeeId(employeeDto.getEmployeeId());
		employee.setEmployeeName(employeeDto.getEmployeeName());
		employee.setEmployeeCode(employeeDto.getEmployeeCode());
		employee.setDepartmentId(employeeDto.getDepartmentId());
		employee.setDesignationId(employeeDto.getDesignationId());

		return employee;
	}

	public static EmployeeDto mapToEmployeeDto(Employee employee, EmployeeDto employeeDto) {

		employeeDto.setEmployeeId(employee.getEmployeeId());
		employeeDto.setEmployeeName(employee.getEmployeeName());
		employeeDto.setEmployeeCode(employee.getEmployeeCode());
		employeeDto.setDepartmentId(employee.getDepartmentId());
		employeeDto.setDesignationId(employee.getDesignationId());

		return employeeDto;
	}

}
