package com.example.demo.mapper;

import com.example.demo.dto.DesignationDto;
import com.example.demo.entity.Designation;

public class DesignationMapper {

	public static Designation mapToDesignation(DesignationDto designationDto, Designation designation) {

		designation.setDesignationId(designationDto.getDesignationId());
		designation.setDesignation(designationDto.getDesignation());
		
		return designation;
	}
	
	public static DesignationDto mapToDesignationDto(Designation designation, DesignationDto designationDto) {

		designationDto.setDesignationId(designation.getDesignationId());
		designationDto.setDesignation(designation.getDesignation());
		
		return designationDto;
	}
}
