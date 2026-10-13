package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Committee;


@Repository("committeerepo")
public interface CommitteeRepository extends JpaRepository<Committee, Long> {

	Optional<Committee> findByCommitteeName(String committeeName);
}
