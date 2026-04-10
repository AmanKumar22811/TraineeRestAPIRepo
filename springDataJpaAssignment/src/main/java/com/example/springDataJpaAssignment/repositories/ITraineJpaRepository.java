package com.example.springDataJpaAssignment.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springDataJpaAssignment.entities.Trainee;

public interface ITraineJpaRepository extends JpaRepository<Trainee, Integer>{
	List<Trainee> findBytarineeName(String name);
}
