package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.DepartmentDto;

public interface IDepartmentService {

	public void createDepartment(DepartmentDto departmentDto);
	
	public List<DepartmentDto> getAllDepartments();
	
	public DepartmentDto getDepartmentbyId(Long deptId);
	
	public List<DepartmentDto> getDepartmentbyCompanyId(Long companyId);
	
	public void updateDepartment(DepartmentDto departmentDto);
}
