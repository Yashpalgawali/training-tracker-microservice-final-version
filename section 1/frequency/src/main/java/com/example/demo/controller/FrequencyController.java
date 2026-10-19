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

import com.example.demo.dto.FrequencyContactInfoDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.entity.Frequency;
import com.example.demo.service.IFrequencyService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "api")
@RequiredArgsConstructor
@Validated
public class FrequencyController {

	private final IFrequencyService committeeServ;

	private final FrequencyContactInfoDto frequencyContactDto;

	@Value("${build.version}")
	private String buildVersion;

	@PostMapping("/")
	public ResponseEntity<ResponseDto> createFrequency(@Valid @RequestBody Frequency frequencyDto) {
		committeeServ.createFrequency(frequencyDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.CREATED.toString(),
				"Frequency " + frequencyDto.getFrequency() + " is created successfully"));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Frequency> getFrequencyById(@PathVariable Long id) {
		var found = committeeServ.getFrequencyById(id);
		return ResponseEntity.status(HttpStatus.OK).body(found);
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<Frequency> getFrequencyByName(@PathVariable String name) {
		var found = committeeServ.getFrequencyByFrequency(name);
		return ResponseEntity.status(HttpStatus.OK).body(found);
	}

	@GetMapping("/")
	public ResponseEntity<List<Frequency>> getAllFrequencys() {
		var found = committeeServ.getAllFrequencies();
		return ResponseEntity.status(HttpStatus.OK).body(found);
	}

	@PutMapping("/")
	public ResponseEntity<ResponseDto> updateFrequency(@Valid @RequestBody Frequency frequencyDto) {
		committeeServ.updateFrequency(frequencyDto);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.toString(),
				"Frequency " + frequencyDto.getFrequency() + " is updated successfully"));
	}

	@GetMapping("/contact-info")
	public ResponseEntity<FrequencyContactInfoDto> getFrequencyContactInfoDto() {

		return ResponseEntity.ok(frequencyContactDto);
	}

	@GetMapping("/build-info")
	public ResponseEntity<String> getBuildVersion() {

		return ResponseEntity.ok(buildVersion);
	}
}
