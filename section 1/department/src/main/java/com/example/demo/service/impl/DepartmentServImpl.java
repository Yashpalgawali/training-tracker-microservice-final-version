package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CompanyDto;
import com.example.demo.dto.DepartmentDto;
import com.example.demo.entity.Department;
import com.example.demo.exception.GlobalException;
import com.example.demo.exception.ResourceAlreadyExistsException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.DepartmentMapper;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.service.IDepartmentService;
import com.example.demo.service.client.CompanyClient;

import lombok.RequiredArgsConstructor;

@Service("deptserv")
@RequiredArgsConstructor
public class DepartmentServImpl implements IDepartmentService {

	private final DepartmentRepository deptrepo;
	
	private final CompanyClient companyClient;

	private final Logger logger = LoggerFactory.getLogger(DepartmentServImpl.class);
	 
	@Override
	public void createDepartment(DepartmentDto departmentDto) {

		Optional<Department> found = deptrepo.findByCompanyIdAndDepartmentName(departmentDto.getCompanyId(), departmentDto.getDepartmentName());
		
		if(found.isPresent()) {
			throw new ResourceAlreadyExistsException("Department", "Name", departmentDto.getDepartmentName());
		}
		
		Department mappedDept = DepartmentMapper.mapToDepartment(departmentDto, new Department());

		Department savedDept = deptrepo.save(mappedDept);
		if (savedDept == null) {
			throw new GlobalException("Department " + departmentDto.getDepartmentName() + " is not created");
		}
	}

	@Override
	public List<DepartmentDto> getAllDepartments(String correlationId) {
		logger.info("CorrelationID in department service {} ", correlationId);
		var list = deptrepo.findAll();
		if (list.size() < 0) {
			throw new ResourceNotFoundException("Department", "List", null);
		}
		return list.stream().map(dept -> {

			ResponseEntity<CompanyDto> companyById = companyClient.getCompanyById(correlationId,dept.getCompanyId());

			@Nullable
			CompanyDto body = companyById.getBody();
			dept.setCompanyId(body.getCompanyId());

			DepartmentDto mappedDto =  DepartmentMapper.mapToDepartmentDto(dept, new DepartmentDto());
			mappedDto.setCompanyName(body.getCompanyName());

			return mappedDto;

		}).collect(Collectors.toList());
	}

	@Override
	public DepartmentDto getDepartmentbyId(String correlationId, Long deptId) {
		logger.info("CorrelationID in department service {} ", correlationId);
		Department found = deptrepo.findById(deptId).orElseThrow(() -> new ResourceNotFoundException("Department", "ID", String.valueOf(deptId)));
		
		ResponseEntity<CompanyDto> companyById = companyClient.getCompanyById(correlationId,found.getCompanyId());

		@Nullable
		CompanyDto body = companyById.getBody();
		DepartmentDto mappedDto =  DepartmentMapper.mapToDepartmentDto(found, new DepartmentDto());
		mappedDto.setCompanyId(body.getCompanyId());
		mappedDto.setCompanyName(body.getCompanyName());
		
		return mappedDto;
	}

	@Override
	public List<DepartmentDto> getDepartmentbyCompanyId(String correlationId, Long companyId) {
		
		logger.info("CorrelationID in department service {} ", correlationId);
		
		List<Department> deptList = deptrepo.findByCompanyId(companyId);
		if(deptList.size() < 0 ) {
			throw new ResourceNotFoundException("Department", "Company ID", String.valueOf(companyId));
		}
		
		return deptList.stream().map(dept-> {
			ResponseEntity<CompanyDto> companyById = companyClient.getCompanyById(correlationId,dept.getCompanyId());

			@Nullable
			CompanyDto body = companyById.getBody();
			System.err.println("the company result is "+body.toString());
			dept.setCompanyId(body.getCompanyId());

			DepartmentDto mappedDto =  DepartmentMapper.mapToDepartmentDto(dept, new DepartmentDto());
			mappedDto.setCompanyName(body.getCompanyName());

			return mappedDto;
		}).collect(Collectors.toList());
	}

	@Override
	public void updateDepartment(DepartmentDto departmentDto) {
		Department mappedDept = DepartmentMapper.mapToDepartment(departmentDto, new Department());

		Department savedDept = deptrepo.save(mappedDept);
		if (savedDept == null) {
			throw new GlobalException("Department " + departmentDto.getDepartmentName() + " is not updated");
		}

	}

}
