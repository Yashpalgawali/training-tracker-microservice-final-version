package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Frequency;

public interface IFrequencyService {

	public void createFrequency(Frequency freq);
	
	public List<Frequency> getAllFrequencies();
	
	public Frequency getFrequencyById(Long id);
	
	public Frequency getFrequencyByFrequency(String frequency);
	
	public void updateFrequency(Frequency freq);
}
