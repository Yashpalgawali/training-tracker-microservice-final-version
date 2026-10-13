package com.example.demo.mapper;

import com.example.demo.dto.CommitteeDto;
import com.example.demo.entity.Committee;

public class CommitteeMapper {

	public static Committee maptoCommittee(CommitteeDto committeeDto, Committee committee) {

		committee.setCommitteeId(committeeDto.getCommitteeId());
		committee.setCommitteeName(committeeDto.getCommitteeName());
		return committee;
	}

	public static CommitteeDto maptoCommitteeDto(Committee committee, CommitteeDto committeeDto) {

		committeeDto.setCommitteeId(committee.getCommitteeId());
		committeeDto.setCommitteeName(committee.getCommitteeName());
		return committeeDto;
	}
}
