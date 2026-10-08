package com.example.demo.mapper;

import com.example.demo.dto.CompanyDto;
import com.example.demo.entity.Company;

public class CompanyMapper {

	public static Company mapToCompany(CompanyDto companyDto, Company company) {

		company.setCompanyName(companyDto.getCompanyName());
		company.setCompanyId(companyDto.getCompanyId());
		return company;
	}

	public static CompanyDto mapToCompanyDto(Company company, CompanyDto companyDto) {

		companyDto.setCompanyId(company.getCompanyId());
		companyDto.setCompanyName(company.getCompanyName());
		return companyDto;
	}
}
