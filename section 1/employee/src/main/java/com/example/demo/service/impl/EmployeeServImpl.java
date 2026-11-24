package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Repository.EmployeeRepository;
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
	public EmployeeDto getEmployeeById(Long id) {
		Employee foundEmp = emprepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "ID", String.valueOf(id)));
		EmployeeDto mappedEmployee = EmployeeMapper.mapToEmployeeDto(foundEmp, new EmployeeDto());
		return mappedEmployee;
	}

	@Override
	public EmployeeDto getEmployeeByEmployeeCode(String code) {
		Employee foundEmp = emprepo.findByEmployeeCode(code).orElseThrow(() -> new ResourceNotFoundException("Employee", "Code", code));
		EmployeeDto mappedEmployee = EmployeeMapper.mapToEmployeeDto(foundEmp, new EmployeeDto());
		return mappedEmployee;
	}

	@Override
	public EmployeeDto getEmployeeByName(String name) {
		Employee foundEmp = emprepo.findByEmployeeName(name).orElseThrow(() -> new ResourceNotFoundException("Employee", "Name", name));
		EmployeeDto mappedEmployee = EmployeeMapper.mapToEmployeeDto(foundEmp, new EmployeeDto());
		return mappedEmployee;
	}

	@Override
	public List<EmployeeDto> getAllEmployees() {
		
		List<Employee> list = emprepo.findAll();
		if(list.size() > 0 )
		{
			list.stream().map(emp -> {
			
				EmployeeDto empDto = new EmployeeDto();
				empDto.setEmployeeId(emp.getEmployeeId());
				empDto.setEmployeeName(emp.getEmployeeName());
				empDto.setEmployeeCode(emp.getEmployeeCode());
				empDto.setDepartmentId(emp.getDepartmentId());
				empDto.setDesignationId(emp.getDesignationId());
				
				return empDto;
			
			}).collect(Collectors.toList());
		}
		
		throw new ResourceNotFoundException("Employee", "List", null);
		
	}

	@Override
	@Transactional
	public void updateEmployee(EmployeeDto empDto) {

		if(!empDto.getEmployeeCode().equals("")) {
			Optional<Employee> found = emprepo.findByEmployeeCode(empDto.getEmployeeCode());
			if(found.isPresent()) {
				throw new ResourceAlreadyExistsException("Employee", "Code", empDto.getEmployeeCode());
			}
			Employee mappedEmployee = EmployeeMapper.mapToEmployee(empDto, new Employee());
			Employee savedEmp = emprepo.save(mappedEmployee);
			if(savedEmp==null) {
				throw new GlobalException("Employee "+empDto.getEmployeeName()+" is not created");
			}			
		}
		else {
			Employee mappedEmployee = EmployeeMapper.mapToEmployee(empDto, new Employee());
			Employee savedEmp = emprepo.save(mappedEmployee);
			if(savedEmp==null) {
				throw new GlobalException("Employee "+empDto.getEmployeeName()+" is not created");
			}			
		}

	}

}
