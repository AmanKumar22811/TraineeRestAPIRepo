package com.example.springDataJpaAssignment.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.springDataJpaAssignment.entities.Trainee;
import com.example.springDataJpaAssignment.repositories.ITraineJpaRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TraineeServiceTest {

    @Mock
    ITraineJpaRepository traineeRepository;

    @InjectMocks
    TraineeService traineeService;

    
    // CREATE
    @Test
    void testSaveTrainee() {
        Trainee trainee = new Trainee();
        trainee.setTarineeName("Aman");

        Mockito.when(traineeRepository.save(trainee)).thenReturn(trainee);

        Trainee saved = traineeService.saveTrainee(trainee);

        assertNotNull(saved);
        assertEquals("Aman", saved.getTarineeName());

        Mockito.verify(traineeRepository, Mockito.times(1)).save(trainee);
    }


    // GET ALL
    @Test
    void testGetAllTrainees() {
        List<Trainee> list = new ArrayList<>();
        list.add(new Trainee());
        list.add(new Trainee());

        Mockito.when(traineeRepository.findAll()).thenReturn(list);

        List<Trainee> result = traineeService.getAllTrainees();

        assertEquals(2, result.size());
        Mockito.verify(traineeRepository).findAll();
    }

    @Test
    void testGetAllTrainees_EmptyList() {
        Mockito.when(traineeRepository.findAll()).thenReturn(Collections.emptyList());

        List<Trainee> result = traineeService.getAllTrainees();

        assertTrue(result.isEmpty());
    }

    // GET BY ID
    @Test
    void testFindTraineeById_Found() {
        Trainee t = new Trainee();
        t.setTraineeId(1);

        Mockito.when(traineeRepository.findById(1)).thenReturn(Optional.of(t));

        Trainee result = traineeService.getTraineeById(1);

        assertNotNull(result);
        assertEquals(1, result.getTraineeId());

        Mockito.verify(traineeRepository).findById(1);
    }

    @Test
    void testFindTraineeById_NotFound() {
        Mockito.when(traineeRepository.findById(2)).thenReturn(Optional.empty());

        Trainee result = traineeService.getTraineeById(2);

        assertNull(result);
    }

    @Test
    void testFindTraineeById_NoException() {
        assertDoesNotThrow(() -> traineeService.getTraineeById(1));
    }

    // FIND BY NAME
    @Test
    void testFindByName_Found() {
        List<Trainee> list = new ArrayList<>();
        list.add(new Trainee());

        Mockito.when(traineeRepository.findBytarineeName("Aman")).thenReturn(list);

        List<Trainee> result = traineeService.getTraineeByName("Aman");

        assertFalse(result.isEmpty());
    }

    @Test
    void testFindByName_NotFound() {
        Mockito.when(traineeRepository.findBytarineeName("Unknown"))
                .thenReturn(Collections.emptyList());

        List<Trainee> result = traineeService.getTraineeByName("Unknown");

        assertTrue(result.isEmpty());
    }

    // UPDATE
    @Test
    void testUpdateTrainee_Success() {
        Trainee existing = new Trainee();
        existing.setTraineeId(1);
        existing.setTarineeName("Old");

        Trainee updatedData = new Trainee();
        updatedData.setTarineeName("New");

        Mockito.when(traineeRepository.findById(1)).thenReturn(Optional.of(existing));
        Mockito.when(traineeRepository.save(Mockito.any())).thenReturn(existing);

        Trainee result = traineeService.updateTrainee(1, updatedData);

        assertNotNull(result);
        assertEquals("New", result.getTarineeName());
    }

    @Test
    void testUpdateTrainee_NotFound() {
        Mockito.when(traineeRepository.findById(5)).thenReturn(Optional.empty());

        Trainee result = traineeService.updateTrainee(5, new Trainee());

        assertNull(result);
    }

    // DELETE
    @Test
    void testDeleteTrainee_Success() {
        Trainee t = new Trainee();
        t.setTraineeId(1);

        Mockito.when(traineeRepository.findById(1)).thenReturn(Optional.of(t));
        Mockito.doNothing().when(traineeRepository).deleteById(1);

        traineeService.deleteTrainee(1);

        Mockito.verify(traineeRepository).deleteById(1);
    }

    @Test
    void testDeleteTrainee_NotFound() {
        Mockito.when(traineeRepository.findById(2)).thenReturn(Optional.empty());

        traineeService.deleteTrainee(2);

        Mockito.verify(traineeRepository, Mockito.never()).deleteById(2);
    }
}