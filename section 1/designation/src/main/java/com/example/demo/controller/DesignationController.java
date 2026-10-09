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

import com.example.demo.dto.DesignationContactInfoDto;
import com.example.demo.dto.DesignationDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.service.IDesignationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "api")
@RequiredArgsConstructor
public class DesignationController {

	private final IDesignationService desigserv;
	
	private final DesignationContactInfoDto designationContact;
	
	@Value(value = "${build.version}")
	private String buildVersion;
	
	@PostMapping("/")
	public ResponseEntity<ResponseDto> createDesignation(@Valid @RequestBody DesignationDto designationDto) {
		desigserv.createDesignation(designationDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.CREATED.toString(),
				"Designation " + designationDto.getDesignation() + " is created successfully"));
	}

	@GetMapping("/")
	public ResponseEntity<List<DesignationDto>> getAllDesignations() {

		var desigList = desigserv.getAllDesignations();
		return ResponseEntity.status(HttpStatus.OK).body(desigList);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DesignationDto> getDesignationById(@PathVariable Long id) {

		DesignationDto dto = desigserv.getDesignationById(id);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<DesignationDto> getDesignationById(@PathVariable String name) {

		DesignationDto dto = desigserv.getDesignationByDesignation(name);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@PutMapping("/")
	public ResponseEntity<ResponseDto> updateDesignation(@Valid @RequestBody DesignationDto designationDto) {
		desigserv.updateDesignation(designationDto);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.toString(),
				"Designation " + designationDto.getDesignation() + " is updated successfully"));
	}
	
	@GetMapping("/contact-info")
	public ResponseEntity<DesignationContactInfoDto> getDesignationContact() {
		
		return  ResponseEntity.ok(designationContact);
	}
	
	@GetMapping("/build-info")
	public ResponseEntity<String> getBuildVersion() {
		
		return  ResponseEntity.ok(buildVersion);
	}
}
