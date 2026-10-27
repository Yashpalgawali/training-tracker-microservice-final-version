package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.TrainingDto;

public interface ITrainingService {

	public void createTraining(TrainingDto training);
	
	public TrainingDto getTrainingById(Long id);
	
	public TrainingDto getTrainingByName(String name);
	
	public List<TrainingDto> getAllTrainings();
	
	public void updateTraining(TrainingDto training);
}
