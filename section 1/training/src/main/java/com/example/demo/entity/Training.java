package com.example.demo.entity;

import java.io.Serializable;

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
@Table(name= "tbl_training", uniqueConstraints = @UniqueConstraint(columnNames = {"training"}))
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Training extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6201160571435120970L;

	@Id
	@SequenceGenerator(name="training_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "training_seq", strategy = GenerationType.AUTO)	
	Long trainingId;
	
	String trainingName;

	Long frequencyId;
}
