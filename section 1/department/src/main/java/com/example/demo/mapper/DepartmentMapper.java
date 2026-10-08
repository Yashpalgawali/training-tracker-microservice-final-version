package com.example.demo.mapper;

import com.example.demo.dto.DepartmentDto;
import com.example.demo.entity.Department;

public class DepartmentMapper {

	public static Department mapToDepartment(DepartmentDto departmentDto, Department department) {
		
		department.setDepartmentId(departmentDto.getDepartmentId());
		department.setDepartmentName(departmentDto.getDepartmentName());
		department.setCompanyId(departmentDto.getCompanyId());
		return  department;
	}
	
	public static DepartmentDto mapToDepartmentDto( Department department,DepartmentDto departmentDto) {
		
		departmentDto.setDepartmentId(department.getDepartmentId());
		departmentDto.setDepartmentName(department.getDepartmentName());
		departmentDto.setCompanyId(department.getCompanyId());
		
		return  departmentDto;
	}
}
