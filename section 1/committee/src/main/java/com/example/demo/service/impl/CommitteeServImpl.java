package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.CommitteeDto;
import com.example.demo.entity.Committee;
import com.example.demo.exception.GlobalException;
import com.example.demo.exception.ResourceAlreadyExistsException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.CommitteeMapper;
import com.example.demo.repository.CommitteeRepository;
import com.example.demo.service.ICommitteeService;

import lombok.RequiredArgsConstructor;

@Service("committeeserv")
@RequiredArgsConstructor
public class CommitteeServImpl implements ICommitteeService {

	private final CommitteeRepository committeerepo;
	
	@Override
	public void createCommittee(CommitteeDto committeeDto) {
		
		if(committeeDto.getCommitteeName().equals("")) {
			throw new GlobalException("Committee name can't be blank");
		}
		
		committeeDto.setCommitteeName(committeeDto.getCommitteeName().trim());
		
		Optional<Committee> found = committeerepo.findByCommitteeName(committeeDto.getCommitteeName());
		if(found.isPresent()) {
			throw new ResourceAlreadyExistsException("Committee", "Name", committeeDto.getCommitteeName());
		}
		
		Committee mappedCommittee = CommitteeMapper.maptoCommittee(committeeDto, new Committee());
		mappedCommittee.setCommitteeId(null);
		Committee savedCommittee = committeerepo.save(mappedCommittee);
		if(savedCommittee==null) {
			throw new GlobalException("Committee "+committeeDto.getCommitteeName()+" is not created");
		}

	}

	@Override
	public CommitteeDto getCommitteeById(Long committeeId) {
        Committee found = committeerepo.findById(committeeId).orElseThrow(()-> new ResourceNotFoundException("Committee", "ID", ""+committeeId));
		return CommitteeMapper.maptoCommitteeDto(found, new CommitteeDto());
	}

	@Override
	public CommitteeDto getCommitteeByName(String committeeName) {
		Committee found = committeerepo.findByCommitteeName(committeeName).orElseThrow(()-> new ResourceNotFoundException("Committee", "Name", committeeName));
		return CommitteeMapper.maptoCommitteeDto(found, new CommitteeDto());
	}

	@Override
	public List<CommitteeDto> getAllCommittees() {
		
		var list = committeerepo.findAll();

		if(list.size() == 0) {
			throw new ResourceNotFoundException("Committee", "Name", null);
		}
		
		return list.stream().map(com -> {
			CommitteeDto dto = new CommitteeDto();			
			dto.setCommitteeId(com.getCommitteeId());
			dto.setCommitteeName(com.getCommitteeName());
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public void updateCommittee(CommitteeDto committeeDto) {
		if(committeeDto.getCommitteeName().equals("")) {
			throw new GlobalException("Committee name can't be blank");
		}
		
		committeeDto.setCommitteeName(committeeDto.getCommitteeName().trim());
		Committee mappedCommittee = CommitteeMapper.maptoCommittee(committeeDto, new Committee());
		Committee savedCommittee = committeerepo.save(mappedCommittee);
		if(savedCommittee==null) {
			throw new GlobalException("Committee "+committeeDto.getCommitteeName()+" is not created");
		}
	}

}
