package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.CommitteeDto;

public interface ICommitteeService {

	public void createCommittee(CommitteeDto committeeDto) ;
	
	public CommitteeDto getCommitteeById(Long committeeId) ;
	
	public CommitteeDto getCommitteeByName(String committeeName) ;
	
	public List<CommitteeDto> getAllCommittees() ;
	
	public void updateCommittee(CommitteeDto committeeDto) ;
}
