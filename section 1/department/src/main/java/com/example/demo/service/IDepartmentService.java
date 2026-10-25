package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.DepartmentDto;

public interface IDepartmentService {

	public void createDepartment(DepartmentDto departmentDto);
	
	public List<DepartmentDto> getAllDepartments(String correlationId);
	
	public DepartmentDto getDepartmentbyId(String correlationId, Long deptId);
	
	public List<DepartmentDto> getDepartmentbyCompanyId(String correlationId, Long companyId);
	
	public void updateDepartment(DepartmentDto departmentDto);
}
