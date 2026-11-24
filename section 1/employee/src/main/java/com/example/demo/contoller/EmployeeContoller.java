package com.example.demo.contoller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.service.IEmployeeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class EmployeeContoller {

	private final IEmployeeService empserv;

	@PostMapping("/")
	public ResponseEntity<ResponseDto> createEmployee(@Valid @RequestBody EmployeeDto empDto) {
		
		empserv.createEmployee(empDto);		
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.CREATED.toString(), "Employee "+empDto.getEmployeeName()+" is created successfully!!!"));
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
		
		EmployeeDto employee = empserv.getEmployeeById(id);		
		return ResponseEntity.status(HttpStatus.OK).body(employee);		
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<EmployeeDto> getEmployeeByName(@PathVariable String name) {
		
		EmployeeDto employee = empserv.getEmployeeByName(name);		
		return ResponseEntity.status(HttpStatus.OK).body(employee);		
	}
	
	@GetMapping("/code/{code}")
	public ResponseEntity<EmployeeDto> getEmployeeByCode(@PathVariable String code) {
		
		EmployeeDto employee = empserv.getEmployeeByEmployeeCode(code);		
		return ResponseEntity.status(HttpStatus.OK).body(employee);		
	}

	
	@PutMapping("/")
	public ResponseEntity<ResponseDto> updateEmployee(@Valid @RequestBody EmployeeDto empDto) {
		
		empserv.updateEmployee(empDto);		
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.toString(), "Employee "+empDto.getEmployeeName()+" is updated successfully!!!"));
		
	}
	
}
