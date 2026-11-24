package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Employee;
import java.util.List;
import java.util.Optional;


@Repository("emprepo")
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	List<Employee> findByDepartmentId(Long departmentId);
	
	List<Employee> findByDesignationId(Long designationId);
	
	List<Employee> findByCreatedBy(String createdBy);
	
	Optional<Employee> findByEmployeeCode(String employeeCode);
	
	Optional<Employee> findByEmployeeName(String employeeName);
	
	List<Employee> findByUpdatedBy(String updatedBy);
}
