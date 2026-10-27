package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CompanyContactInfoDto;
import com.example.demo.dto.CompanyDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.service.ICompanyService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "api", produces = { MediaType.APPLICATION_JSON_VALUE })
@RequiredArgsConstructor
@Validated
public class CompanyController {

	private final ICompanyService compserv;

	private final CompanyContactInfoDto companyContact;

	private final Logger logger = LoggerFactory.getLogger(CompanyController.class);

	@Value(value = "${build.version}")
	private String buildVersion;

	@PostMapping("/")
	public ResponseEntity<ResponseDto> createCompany(@Valid @RequestBody CompanyDto companyDto) {

		compserv.createCompany(companyDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.CREATED.toString(),
				"Company " + companyDto.getCompanyName() + " is created successfully"));
	}

	@GetMapping("/{id}")
	public ResponseEntity<CompanyDto> getCompanyById(
			@RequestHeader("trainingtracker-correlation-id") String correlationId, @PathVariable Long id) {
		logger.debug("inside getDepartmentById() in department service correlationId {}", correlationId);
		CompanyDto company = compserv.getCompanyById(correlationId, id);
		return ResponseEntity.status(HttpStatus.OK).body(company);
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<CompanyDto> getCompanyByName(
			@RequestHeader("trainingtracker-correlation-id") String correlationId, @PathVariable String name) {
		logger.debug("inside getDepartmentById() in department service correlationId {}", correlationId);
		CompanyDto company = compserv.getCompanyByName(correlationId, name);
		return ResponseEntity.status(HttpStatus.OK).body(company);
	}

	@GetMapping("/")
	public ResponseEntity<List<CompanyDto>> getAllCompanies() {

		var list = compserv.getAllCompanies();
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

	@PutMapping("/")
	public ResponseEntity<ResponseDto> updateCompany(@Valid @RequestBody CompanyDto companyDto) {

		compserv.updateCompany(companyDto);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.toString(),
				"Company " + companyDto.getCompanyName() + " is updated successfully"));
	}

	@GetMapping("/contact-info")
	public ResponseEntity<CompanyContactInfoDto> getCompanyContact() {

		return ResponseEntity.ok(companyContact);
	}

	@GetMapping("/build-info")
	public ResponseEntity<String> getBuildVersion() {

		return ResponseEntity.ok(buildVersion);
	}
}
