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
@Table(name= "tbl_designation", uniqueConstraints = @UniqueConstraint(columnNames = {"designation"}))
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Designation extends BaseEntity{

	@Id
	@SequenceGenerator(name="designation_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "designation_seq", strategy = GenerationType.AUTO)
	Long designationId;
	
	String designation;
}
