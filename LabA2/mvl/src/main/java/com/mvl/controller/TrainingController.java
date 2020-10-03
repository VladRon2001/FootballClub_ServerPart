package com.mvl.controller;


import com.mvl.services.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class TrainingController {
    final private TrainingService trainingService;

    @Autowired
    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @PutMapping("/training/{id}")
    public ResponseEntity<Void> train(@PathVariable UUID id) {
        trainingService.train(id);
        return ResponseEntity.noContent().build();
    }
}
