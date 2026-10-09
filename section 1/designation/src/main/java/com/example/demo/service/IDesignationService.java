package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.DesignationDto;

public interface IDesignationService {

	public void createDesignation(DesignationDto designationDto);
	
	public DesignationDto getDesignationById(Long desigId);
	
	public DesignationDto getDesignationByDesignation(String designation);
	
	public List<DesignationDto> getAllDesignations();
	
	public void updateDesignation(DesignationDto designationDto);
}
