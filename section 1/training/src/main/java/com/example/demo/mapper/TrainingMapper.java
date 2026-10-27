package com.example.demo.mapper;

import com.example.demo.dto.TrainingDto;
import com.example.demo.entity.Training;

public class TrainingMapper {

	public static Training mapToTraining(TrainingDto trainingDto, Training training) {
		
		training.setTrainingId(trainingDto.getTrainingId());
		training.setTrainingName(trainingDto.getTrainingName());
		training.setFrequencyId(trainingDto.getFrequencyId());
		return training;
	}
	
	public static TrainingDto mapToTrainingDto(Training training, TrainingDto trainingDto) {
		
		trainingDto.setTrainingId(training.getTrainingId());
		trainingDto.setTrainingName(training.getTrainingName());
		trainingDto.setFrequencyId(training.getFrequencyId());
		return trainingDto;
	}
}
