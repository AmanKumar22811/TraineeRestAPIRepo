package com.example.springDataJpaAssignment.controller;

import com.example.springDataJpaAssignment.entities.Trainee;
import com.example.springDataJpaAssignment.service.TraineeService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainees")
public class Controller {

    @Autowired
    private TraineeService service;

    // 1 Insert
    @PostMapping
    public ResponseEntity<Trainee> addTrainee(@Valid @RequestBody Trainee trainee) {
        Trainee saved = service.saveTrainee(trainee);
        return new ResponseEntity<>(saved, HttpStatus.CREATED); // 201
    }

    // 2 List All
    @GetMapping
    public ResponseEntity<List<Trainee>> getAll() {
        List<Trainee> list = service.getAllTrainees();

        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
        }
        return new ResponseEntity<>(list, HttpStatus.OK); // 200
    }

    // 3 Find by ID
    @GetMapping("/{id}")
    public ResponseEntity<Trainee> getById(@PathVariable int id) {
        Trainee t = service.getTraineeById(id);

        if (t != null) {
            return new ResponseEntity<>(t, HttpStatus.OK); // 200
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
        }
    }

    // 4 Find by Name
    @GetMapping("/byname")
    public ResponseEntity<List<Trainee>> getByName(@RequestParam("name") String name) {
        List<Trainee> list = service.getTraineeByName(name);

        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
        }
        return new ResponseEntity<>(list, HttpStatus.OK); // 200
    }

    // 5 Update
    @PutMapping("/{id}")
    public ResponseEntity<Trainee> update(@PathVariable int id, @RequestBody Trainee trainee) {
        Trainee updated = service.updateTrainee(id, trainee);

        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK); // 200
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
        }
    }

    // 6 Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        Trainee existing = service.getTraineeById(id);

        if (existing != null) {
            service.deleteTrainee(id);
            return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK); // 200
        } else {
            return new ResponseEntity<>("Trainee Not Found", HttpStatus.NOT_FOUND); // 404
        }
    }
}