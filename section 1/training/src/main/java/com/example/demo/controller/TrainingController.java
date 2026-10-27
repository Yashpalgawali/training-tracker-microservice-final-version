package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.TrainingDto;
import com.example.demo.service.ITrainingService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class TrainingController {

	private final ITrainingService trainingserv;

	@PostMapping("/")
	public ResponseEntity<ResponseDto> createTraining(@Valid @RequestBody TrainingDto trainingDto ) {
		trainingserv.createTraining(trainingDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.CREATED.toString(), "Training "+trainingDto.getTrainingName()+" is created successfully"));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TrainingDto> getTrainingById(@PathVariable Long id ) {
		TrainingDto trainingDto = trainingserv.getTrainingById(id);
		return ResponseEntity.status(HttpStatus.OK).body(trainingDto);
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<TrainingDto> getTrainingByTrainingName(@PathVariable String name ) {
		TrainingDto trainingDto = trainingserv.getTrainingByName(name);
		return ResponseEntity.status(HttpStatus.OK).body(trainingDto);
	}

	@GetMapping("/")
	public ResponseEntity<List<TrainingDto>> getAllTrainings() {
		var trainingDto = trainingserv.getAllTrainings();
		return ResponseEntity.status(HttpStatus.OK).body(trainingDto);
	}

	@PutMapping("/")
	public ResponseEntity<ResponseDto> updateTraining(@Valid @RequestBody TrainingDto trainingDto ) {
		trainingserv.updateTraining(trainingDto);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.toString(), "Training "+trainingDto.getTrainingName()+" is updated successfully"));
	}
}
