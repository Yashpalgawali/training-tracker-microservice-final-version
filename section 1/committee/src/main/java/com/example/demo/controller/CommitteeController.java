package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CommitteeContactInfoDto;
import com.example.demo.dto.CommitteeDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.service.ICommitteeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "api")
@RequiredArgsConstructor
@Validated
public class CommitteeController {

	private final ICommitteeService committeeServ;

	private CommitteeContactInfoDto committeeContactDto;

	@Value("${build.version}")
	private String buildVersion;

	@PostMapping("/")
	public ResponseEntity<ResponseDto> createCommittee(@Valid @RequestBody CommitteeDto committeeDto) {
		committeeServ.createCommittee(committeeDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.CREATED.toString(),
				"Committee " + committeeDto.getCommitteeName() + " is created successfully"));
	}

	@GetMapping("/{id}")
	public ResponseEntity<CommitteeDto> getCommitteeById(@PathVariable Long id) {
		var found = committeeServ.getCommitteeById(id);
		return ResponseEntity.status(HttpStatus.OK).body(found);
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<CommitteeDto> getCommitteeByName(@PathVariable String name) {
		var found = committeeServ.getCommitteeByName(name);
		return ResponseEntity.status(HttpStatus.OK).body(found);
	}

	@GetMapping("/")
	public ResponseEntity<List<CommitteeDto>> getAllCommittees() {
		var found = committeeServ.getAllCommittees();
		return ResponseEntity.status(HttpStatus.OK).body(found);
	}

	@PutMapping("/")
	public ResponseEntity<ResponseDto> updateCommittee(@Valid @RequestBody CommitteeDto committeeDto) {
		committeeServ.updateCommittee(committeeDto);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.toString(),
				"Committee " + committeeDto.getCommitteeName() + " is updated successfully"));
	}

	@GetMapping("/contact-info")
	public ResponseEntity<CommitteeContactInfoDto> getCommitteeContactInfoDto() {

		return ResponseEntity.ok(committeeContactDto);
	}

	@GetMapping("/build-info")
	public ResponseEntity<String> getBuildVersion() {

		return ResponseEntity.ok(buildVersion);
	}
}
