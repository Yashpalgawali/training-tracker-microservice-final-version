package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Frequency;
import com.example.demo.exception.GlobalException;
import com.example.demo.exception.ResourceAlreadyExistsException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.FrequencyRepository;
import com.example.demo.service.IFrequencyService;

import lombok.RequiredArgsConstructor;

@Service("freqserv")
@RequiredArgsConstructor
public class FrequencyServImpl implements IFrequencyService {

	private final FrequencyRepository frequencyrepo;

	@Override
	public void createFrequency(Frequency freq) {

		Optional<Frequency> byFrequency = frequencyrepo.findByFrequency(freq.getFrequency());
		if (byFrequency.isPresent()) {
			throw new ResourceAlreadyExistsException("Frequency", "Frequency", freq.getFrequency());
		}
		 
		Frequency savedFrequency = frequencyrepo.save(freq);
		
		if(savedFrequency==null) {
			throw new GlobalException("Frequency "+freq.getFrequency()+" is not created");
		}
		
	}

	@Override
	public List<Frequency> getAllFrequencies() {
		var list = frequencyrepo.findAll();
		if(list.size() < 0) {
			throw new ResourceNotFoundException("Frequency", "frequency" , null);
		}
		
		return list;
	}

	@Override
	public Frequency getFrequencyById(Long id) {
		return frequencyrepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Frequency", "ID", ""+id));
	}

	@Override
	public Frequency getFrequencyByFrequency(String frequency) {
		return frequencyrepo.findByFrequency(frequency).orElseThrow(()-> new ResourceNotFoundException("Frequency", "Frequency", frequency));
	}

	@Override
	public void updateFrequency(Frequency freq) {
		Frequency savedFrequency = frequencyrepo.save(freq);
		
		if(savedFrequency==null) {
			throw new GlobalException("Frequency "+freq.getFrequency()+" is not updated");
		}

	}

}
