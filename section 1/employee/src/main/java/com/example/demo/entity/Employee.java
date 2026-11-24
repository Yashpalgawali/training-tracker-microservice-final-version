package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "tbl_employee",uniqueConstraints = @UniqueConstraint(columnNames = {"employeeCode"})	)
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee extends BaseEntity {

	@Id
	@SequenceGenerator(name = "employee_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "employee_seq", strategy = GenerationType.AUTO)
	Long employeeId;
	
	String employeeName;
	
	String employeeCode;
	
	Long designationId;
	
	Long departmentId;
}
