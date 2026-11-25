package com.example.demo.contoller;

import java.util.List;

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

import com.example.demo.dto.EmployeeContactInfoDto;
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

	private final EmployeeContactInfoDto empContactInfoDto;

	@Value(value = "${build.version}")
	private String buildVersion;

	@PostMapping("/")
	public ResponseEntity<ResponseDto> createEmployee(@Valid @RequestBody EmployeeDto empDto) {

		empserv.createEmployee(empDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.CREATED.toString(),
				"Employee " + empDto.getEmployeeName() + " is created successfully!!!"));

	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@RequestHeader("trainingtracker-correlation-id")  String correlationId, @PathVariable Long id) {

		EmployeeDto employee = empserv.getEmployeeById(correlationId,id);
		return ResponseEntity.status(HttpStatus.OK).body(employee);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<EmployeeDto>> getAllEmployees(@RequestHeader("trainingtracker-correlation-id")  String correlationId) {

		List<EmployeeDto> employeeList = empserv.getAllEmployees(correlationId);
		return ResponseEntity.status(HttpStatus.OK).body(employeeList);
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<EmployeeDto> getEmployeeByName(@RequestHeader("trainingtracker-correlation-id")  String correlationId, @PathVariable String name) {

		EmployeeDto employee = empserv.getEmployeeByName(correlationId, name);
		return ResponseEntity.status(HttpStatus.OK).body(employee);
	}

	@GetMapping("/code/{code}")
	public ResponseEntity<EmployeeDto> getEmployeeByCode(@RequestHeader("trainingtracker-correlation-id")  String correlationId, @PathVariable String code) {

		EmployeeDto employee = empserv.getEmployeeByEmployeeCode(correlationId, code);
		return ResponseEntity.status(HttpStatus.OK).body(employee);
	}

	@PutMapping("/")
	public ResponseEntity<ResponseDto> updateEmployee(@Valid @RequestBody EmployeeDto empDto) {

		empserv.updateEmployee(empDto);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.toString(),
				"Employee " + empDto.getEmployeeName() + " is updated successfully!!!"));
	}

	@GetMapping("/contact-info")
	public ResponseEntity<EmployeeContactInfoDto> getEmployeeContact() {

		return ResponseEntity.ok(empContactInfoDto);
	}

	@GetMapping("/build-info")
	public ResponseEntity<String> getBuildVersion() {

		return ResponseEntity.ok(buildVersion);
	}
}
