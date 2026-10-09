package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.DesignationDto;
import com.example.demo.entity.Designation;
import com.example.demo.exception.GlobalException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.DesignationMapper;
import com.example.demo.repository.DesignationRepository;
import com.example.demo.service.IDesignationService;

import lombok.RequiredArgsConstructor;

@Service("desigserv")
@RequiredArgsConstructor
public class DesigantionServImpl implements IDesignationService {

	private final DesignationRepository desigRepo;
		
	@Override
	public void createDesignation(DesignationDto designationDto) {
		if(designationDto.getDesignation().equals("")) {
			throw new GlobalException("Desigantion can't be blank");
		}
		
		Designation mappedDesignation = DesignationMapper.mapToDesignation(designationDto, new Designation());
		Designation savedDesignation = desigRepo.save(mappedDesignation);
		if(savedDesignation==null)
		{
			throw new GlobalException("Designation "+designationDto.getDesignation()+" is not created");
		}
	}

	@Override
	public DesignationDto getDesignationById(Long desigId) {
		Designation designation = desigRepo.findById(desigId).orElseThrow(()-> new ResourceNotFoundException("Designation", "Id", String.valueOf(desigId)));
		return DesignationMapper.mapToDesignationDto(designation, new DesignationDto());
	}

	@Override
	public DesignationDto getDesignationByDesignation(String designation) {
		Designation found= desigRepo.findByDesignation(designation).orElseThrow(()-> new ResourceNotFoundException("Designation", "Name", designation));
		return DesignationMapper.mapToDesignationDto(found, new DesignationDto());
	}

	@Override
	public List<DesignationDto> getAllDesignations() {
		var list = desigRepo.findAll();
		System.err.println("size "+list.size());
		if( list.size() <= 0) {
			throw new ResourceNotFoundException("Designation", "List", null);
		}
		else {
			return list.stream().map(desig->{
			
				DesignationDto dto = new  DesignationDto();
				
				dto.setDesignationId(desig.getDesignationId());
				dto.setDesignation(desig.getDesignation());
				return dto;
				
			}).collect(Collectors.toList());			
		}		
	}

	@Override
	@Transactional
	public void updateDesignation(DesignationDto designationDto) {
		if(designationDto.getDesignation().equals("")) {
			throw new GlobalException("Desigantion can't be blank");
		}
		
		Designation mappedDesignation = DesignationMapper.mapToDesignation(designationDto, new Designation());
		Designation savedDesignation = desigRepo.save(mappedDesignation);
		if(savedDesignation==null)
		{
			throw new GlobalException("Designation "+designationDto.getDesignation()+" is not updated");
		}
	}

}
