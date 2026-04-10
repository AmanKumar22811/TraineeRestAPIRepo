package com.example.springDataJpaAssignment.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springDataJpaAssignment.entities.Trainee;
import com.example.springDataJpaAssignment.repositories.ITraineJpaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TraineeService {

    @Autowired
    private ITraineJpaRepository repository;

    // Insert
    public Trainee saveTrainee(Trainee trainee) {
        return repository.save(trainee);
    }

    // List All
    public List<Trainee> getAllTrainees() {
        return repository.findAll();
    }

    // Find by ID
    public Trainee getTraineeById(int id) {
    	Optional<Trainee> t = repository.findById(id);
    	return t.orElse(null);
    }

    // Find by Name
    public List<Trainee> getTraineeByName(String name) {
        return repository.findBytarineeName(name);
    }

    // Delete
    public void deleteTrainee(int id) {
        Optional<Trainee> t = repository.findById(id);

        if (t.isPresent()) {
        	repository.deleteById(id);
        }
    }

    // Update
    public Trainee updateTrainee(int id, Trainee newData) {
        return repository.findById(id).map(t -> {
            t.setTarineeName(newData.getTarineeName());
            t.setDomain(newData.getDomain());
            t.setLocation(newData.getLocation());
            return repository.save(t);
        }).orElse(null);
    }
}
