package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
	
	private final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
	
	@Value("${build.version}")
	private String buildVersion;
	
	@PostMapping("/")
	public ResponseEntity<ResponseDto> createDepartment(@Valid @RequestBody DepartmentDto deptDto) {
		deptserv.createDepartment(deptDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.CREATED.toString() , "The Department "+deptDto.getDepartmentName()+" is created successfully"));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DepartmentDto> getDepartmentById(@RequestHeader("trainingtracker-correlation-id")  String correlationId,@PathVariable Long id) {
		
		logger.debug("inside getDepartmentById() in department service correlationId {}",correlationId);
		var dept = deptserv.getDepartmentbyId(correlationId,id);
		return ResponseEntity.status(HttpStatus.OK).body(dept);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<DepartmentDto>> getAllDepartments(@RequestHeader("trainingtracker-correlation-id")  String correlationId) {
		logger.debug("inside getDepartmentById() in department service correlationId {}",correlationId);
		var deptList = deptserv.getAllDepartments(correlationId);
		return ResponseEntity.status(HttpStatus.OK).body(deptList);
	}
	
	@GetMapping("/company/{companyid}")
	public ResponseEntity<List<DepartmentDto>> getDepartmentByCompanyId(@RequestHeader("trainingtracker-correlation-id")  String correlationId,@PathVariable Long companyid) {
		logger.debug("inside getDepartmentById() in department service correlationId {}",correlationId);
		var dept = deptserv.getDepartmentbyCompanyId(correlationId,companyid);
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
