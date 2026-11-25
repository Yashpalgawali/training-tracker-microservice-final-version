package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.clients.DepartmentClient;
import com.example.demo.clients.DesignationClient;
import com.example.demo.dto.DepartmentDto;
import com.example.demo.dto.DesignationDto;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.entity.Employee;
import com.example.demo.exception.GlobalException;
import com.example.demo.exception.ResourceAlreadyExistsException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.service.IEmployeeService;

import lombok.RequiredArgsConstructor;

@Service("empserv")
@RequiredArgsConstructor
public class EmployeeServImpl implements IEmployeeService {

	private final EmployeeRepository emprepo;

	private final DesignationClient desigClient; 
	
	private final DepartmentClient deptClient;
	
	@Override
	public void createEmployee(EmployeeDto empDto) {

		if (!empDto.getEmployeeCode().equals("")) {
			Optional<Employee> found = emprepo.findByEmployeeCode(empDto.getEmployeeCode());
			if (found.isPresent()) {
				throw new ResourceAlreadyExistsException("Employee", "Code", empDto.getEmployeeCode());
			}
			Employee mappedEmployee = EmployeeMapper.mapToEmployee(empDto, new Employee());
			mappedEmployee.setEmployeeId(null);
			Employee savedEmp = emprepo.save(mappedEmployee);
			if (savedEmp == null) {
				throw new GlobalException("Employee " + empDto.getEmployeeName() + " is not created");
			}
		} else {
			Employee mappedEmployee = EmployeeMapper.mapToEmployee(empDto, new Employee());
			mappedEmployee.setEmployeeId(null);
			Employee savedEmp = emprepo.save(mappedEmployee);
			if (savedEmp == null) {
				throw new GlobalException("Employee " + empDto.getEmployeeName() + " is not created");
			}
		}

	}

	@Override
	public EmployeeDto getEmployeeById(String correlationId, Long id) {
		Employee foundEmp = emprepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "ID", String.valueOf(id)));
		EmployeeDto mappedEmployee = EmployeeMapper.mapToEmployeeDto(foundEmp, new EmployeeDto());
		
		ResponseEntity<DesignationDto> designationById = desigClient.getDesignationById(mappedEmployee.getDesignationId());
		if(designationById!=null) {
			DesignationDto desigDto = designationById.getBody();
			mappedEmployee.setDesignation(desigDto.getDesignation());
		}
		else {
			mappedEmployee.setDesignation("");
		}
		
		ResponseEntity<DepartmentDto> departmentById = deptClient.getDepartmentById(correlationId, mappedEmployee.getDepartmentId());
		if(departmentById!=null) {
			System.err.println("Department Found "+departmentById.getBody().toString());
			DepartmentDto deptDto = departmentById.getBody();
			mappedEmployee.setDepartmentName(deptDto.getDepartmentName());
			mappedEmployee.setCompanyName(deptDto.getCompanyName());
		}
		else {
			System.err.println("Department NOT Found ");
			mappedEmployee.setDepartmentName("");
			mappedEmployee.setCompanyName("");
		}

		return mappedEmployee;
	}

	@Override
	public EmployeeDto getEmployeeByEmployeeCode(String correlationId, String code) {
		Employee foundEmp = emprepo.findByEmployeeCode(code)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "Code", code));
		EmployeeDto mappedEmployee = EmployeeMapper.mapToEmployeeDto(foundEmp, new EmployeeDto());
		ResponseEntity<DesignationDto> designationById = desigClient.getDesignationById(mappedEmployee.getDesignationId());
		if(designationById!=null) {
			DesignationDto desigDto = designationById.getBody();
			mappedEmployee.setDesignation(desigDto.getDesignation());
		}
		else {
			mappedEmployee.setDesignation("");
		}
		ResponseEntity<DepartmentDto> departmentById = deptClient.getDepartmentById(correlationId, mappedEmployee.getDepartmentId());
		if(departmentById!=null) {
			DepartmentDto deptDto = departmentById.getBody();
			mappedEmployee.setDepartmentName(deptDto.getDepartmentName());
			mappedEmployee.setCompanyName(deptDto.getCompanyName());
		}
		else {
			mappedEmployee.setDepartmentName("");
			mappedEmployee.setCompanyName("");
		}
		return mappedEmployee;
	}

	@Override
	public EmployeeDto getEmployeeByName(String correlationId, String name) {
		Employee foundEmp = emprepo.findByEmployeeName(name)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "Name", name));
		EmployeeDto mappedEmployee = EmployeeMapper.mapToEmployeeDto(foundEmp, new EmployeeDto());
		ResponseEntity<DesignationDto> designationById = desigClient.getDesignationById(mappedEmployee.getDesignationId());
		if(designationById!=null) {
			DesignationDto desigDto = designationById.getBody();
			mappedEmployee.setDesignation(desigDto.getDesignation());
		}
		else {
			mappedEmployee.setDesignation("");
		}
		
		ResponseEntity<DepartmentDto> departmentById = deptClient.getDepartmentById(correlationId, mappedEmployee.getDepartmentId());
		if(departmentById!=null) {
			DepartmentDto deptDto = departmentById.getBody();
			mappedEmployee.setDepartmentName(deptDto.getDepartmentName());
			mappedEmployee.setCompanyName(deptDto.getCompanyName());
		}
		else {
			mappedEmployee.setDepartmentName("");
			mappedEmployee.setCompanyName("");
		}
		return mappedEmployee;
	}

	@Override
	public List<EmployeeDto> getAllEmployees(String correlationId) {

		List<Employee> list = emprepo.findAll();
		if (list.size() > 0) {
			return list.stream().map(emp -> {

				EmployeeDto empDto = new EmployeeDto();
				empDto.setEmployeeId(emp.getEmployeeId());
				empDto.setEmployeeName(emp.getEmployeeName());
				empDto.setEmployeeCode(emp.getEmployeeCode());
				empDto.setDepartmentId(emp.getDepartmentId());
				empDto.setDesignationId(emp.getDesignationId());
				
				ResponseEntity<DesignationDto> designationById = desigClient.getDesignationById(emp.getDesignationId());
				if(designationById!=null) {
					
					DesignationDto desigDto = designationById.getBody();
					empDto.setDesignation(desigDto.getDesignation());
				}
				else {
					empDto.setDesignation("");
				}
				ResponseEntity<DepartmentDto> departmentById = deptClient.getDepartmentById(correlationId, empDto.getDepartmentId());
				if(departmentById!=null) {
					DepartmentDto deptDto = departmentById.getBody();
					empDto.setDepartmentName(deptDto.getDepartmentName());
					empDto.setCompanyName(deptDto.getCompanyName());
				}
				else {
					empDto.setDepartmentName("");
					empDto.setCompanyName("");
				}
				return empDto;

			}).collect(Collectors.toList());
		}
	
		throw new ResourceNotFoundException("Employee", "List", null);

	}

	@Override
	@Transactional
	public void updateEmployee(EmployeeDto empDto) {

		Employee mappedEmployee = EmployeeMapper.mapToEmployee(empDto, new Employee());
		Employee savedEmp = emprepo.save(mappedEmployee);
		if (savedEmp == null) {
			throw new GlobalException("Employee " + empDto.getEmployeeName() + " is not updated");
		}
	}

}
