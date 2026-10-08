package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.DepartmentContactInfo;
import com.example.demo.dto.DepartmentDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.service.IDepartmentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class DepartmentController {

	private final IDepartmentService deptserv;

	private final DepartmentContactInfo departmentContactInfo;
	
	@Value("${build.version}")
	private String buildVersion;
	
	@PostMapping("/")
	public ResponseEntity<ResponseDto> createDepartment(@Valid @RequestBody DepartmentDto deptDto) {
		deptserv.createDepartment(deptDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.CREATED.toString() , "The Department "+deptDto.getDepartmentName()+" is created successfully"));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Long id) {
		var dept = deptserv.getDepartmentbyId(id);
		return ResponseEntity.status(HttpStatus.OK).body(dept);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
		var deptList = deptserv.getAllDepartments();
		return ResponseEntity.status(HttpStatus.OK).body(deptList);
	}
	
	@GetMapping("/company/{companyid}")
	public ResponseEntity<List<DepartmentDto>> getDepartmentByCompanyId(@PathVariable Long companyid) {
		var dept = deptserv.getDepartmentbyCompanyId(companyid);
		return ResponseEntity.status(HttpStatus.OK).body(dept);
	}
	
	@PutMapping("/")
	public ResponseEntity<ResponseDto> updateDepartment(@Valid @RequestBody DepartmentDto deptDto) {
		deptserv.createDepartment(deptDto);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.toString() , "The Department "+deptDto.getDepartmentName()+" is updated successfully"));
	}

	@GetMapping("/contact-info")
	public ResponseEntity<DepartmentContactInfo> getDepartmentByContactinfo() {
		
		return ResponseEntity.status(HttpStatus.OK).body(departmentContactInfo);
	}
	
	@GetMapping("/build-info")
	public ResponseEntity<String> getBuildInfo() {		
		return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
	}

}
