package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.client.FrequencyClient;
import com.example.demo.dto.Frequency;
import com.example.demo.dto.TrainingDto;
import com.example.demo.entity.Training;
import com.example.demo.exception.GlobalException;
import com.example.demo.exception.ResourceAlreadyExistsException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.TrainingMapper;
import com.example.demo.repository.TrainingRepository;
import com.example.demo.service.ITrainingService;

import lombok.RequiredArgsConstructor;

@Service("trainingserv")
@RequiredArgsConstructor
public class TrainingServImpl implements ITrainingService {

	private final TrainingRepository trainingrepo; 
	
	private final FrequencyClient frequencyClient;
	
	@Override
	public void createTraining(TrainingDto training) {

		if(training.getTrainingName()== null || training == null || training.getTrainingName().trim().isEmpty()) {
			throw new GlobalException("Training name can't be blank or null");
		}
		StringBuffer sb = new StringBuffer();
		sb.append(training.getTrainingName().trim());
		training.setTrainingName(sb.toString());
		
		Optional<Training> found = trainingrepo.findByTrainingName(training.getTrainingName());
		
		if(found.isPresent()) {
			throw new ResourceAlreadyExistsException("Training", "Name", training.getTrainingName());
		}
		
		Training mappedTraining = TrainingMapper.mapToTraining(training, new Training());
		Training savedTraining = trainingrepo.save(mappedTraining);
		if(savedTraining ==null) {
			throw new GlobalException("Training "+training.getTrainingName()+" is not created");
		}
	}

	@Override
	public TrainingDto getTrainingById(Long id) {
		
		Training found = trainingrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Training", "ID", String.valueOf(id)));
		TrainingDto mapped = TrainingMapper.mapToTrainingDto(found, new TrainingDto());
		
		ResponseEntity<Frequency> frequencyById = frequencyClient.getFrequencyById(found.getFrequencyId());
		
		if(frequencyById != null) {
			Frequency freq = frequencyById.getBody();
			mapped.setFrequency(freq.getFrequency());
			mapped.setFrequencyId(freq.getFrequencyId());
		}
		else {
			mapped.setFrequency("");
			mapped.setFrequencyId(found.getFrequencyId());
		}
		return mapped;
	}

	@Override
	public TrainingDto getTrainingByName(String name) {
		Training found = trainingrepo.findByTrainingName(name).orElseThrow(() -> new ResourceNotFoundException("Training", "Name", name));
		return TrainingMapper.mapToTrainingDto(found, new TrainingDto());
	}

	@Override
	public List<TrainingDto> getAllTrainings() {

		var list = trainingrepo.findAll();
		if(list.size() < 0) {
			throw new ResourceNotFoundException("Training", "List", "");
		}
		else {
			return list.stream().map(training-> {
				TrainingDto mapped = TrainingMapper.mapToTrainingDto(training, new TrainingDto());
				ResponseEntity<Frequency> frequencyById = frequencyClient.getFrequencyById(training.getFrequencyId());
				Frequency  freq =  frequencyById.getBody();
				if(freq.getFrequency()==null ) {
					mapped.setFrequency("");
					mapped.setFrequencyId(freq.getFrequencyId());
				}
				else {
					mapped.setFrequency(freq.getFrequency());
					mapped.setFrequencyId(freq.getFrequencyId());
				}
				return mapped;				
			}).collect(Collectors.toList());
		}		
	}

	@Override
	public void updateTraining(TrainingDto training) {

		if(training.getTrainingName()== null || training == null || training.getTrainingName().trim().isEmpty()) {
			throw new GlobalException("Training name can't be blank or null");
		}
		
		Training mappedTraining = TrainingMapper.mapToTraining(training, new Training());
		Training savedTraining = trainingrepo.save(mappedTraining);
		if(savedTraining ==null) {
			throw new GlobalException("Training "+training.getTrainingName()+" is not created");
		}

	}

}
