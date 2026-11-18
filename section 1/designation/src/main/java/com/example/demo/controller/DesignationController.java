package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "api", produces = { MediaType.APPLICATION_JSON_VALUE })
@RequiredArgsConstructor
public class DesignationController {

	private final IDesignationService desigserv;

	private final DesignationContactInfoDto designationContactInfo;

	@Value(value = "${build.version}")
	private String buildVersion;

	private static final Logger logger = LoggerFactory.getLogger(DesignationController.class);
	
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

	@Retry(name = "getDesignationContactInfo", fallbackMethod = "getDesignationContactInfoFallBack")
	@GetMapping("/contact-info")
	public ResponseEntity<DesignationContactInfoDto> getDesignationContactInfo() {

		return ResponseEntity.ok(designationContactInfo);
	}
	
	@RateLimiter(name = "getBuildVersion", fallbackMethod = "getBuildVersionFallBack")
	@Bulkhead(name = "getBuildVersion", fallbackMethod = "getBuildVersionBulkFallBack")	
	@GetMapping("/build-info")
	public ResponseEntity<String> getBuildVersion() throws InterruptedException {
		logger.info("getBuildVersion() is invoked");
		Thread.sleep(6000);
		return ResponseEntity.ok("1.1");
	}
	
	public ResponseEntity<String> getBuildVersionFallBack(Throwable throwable ) {

		return ResponseEntity.ok("2.3");
	}
	
	public ResponseEntity<String> getBuildVersionBulkFallBack(Throwable throwable ) {
		logger.info("getBuildVersionBulkFallBack() is invoked");
		return ResponseEntity.ok("getBuildVersionBulk API FallBack. Too many concurrent requests, please try again later ");
	}

}
