package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Company;

@Repository("comprepo")
public interface CompanyRepository extends JpaRepository<Company, Long> {

	Optional<Company> findByCompanyName(String companyName);
}
