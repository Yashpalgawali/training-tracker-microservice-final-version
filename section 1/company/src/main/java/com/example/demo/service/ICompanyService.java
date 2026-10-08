package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.CompanyDto;

public interface ICompanyService {

	public void createCompany(CompanyDto companyDto);
	
	public CompanyDto getCompanyById(Long companyId);
	
	public CompanyDto getCompanyByName(String companyName);
	
	public List<CompanyDto> getAllCompanies();
	
	public void updateCompany(CompanyDto companyDto);
	
}
