package com.mvl.controller;

import com.mvl.models.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mvl.services.QualificationCourseService;

import java.util.UUID;

@RestController
public class QualificationCourseController {
    private final QualificationCourseService qualificationCourseService;

    @Autowired
    public QualificationCourseController(QualificationCourseService qualificationCourseService) {
        this.qualificationCourseService = qualificationCourseService;
    }

    @GetMapping("/qualificationCourse/{id}")
    public void qualificationCourse(@PathVariable UUID id) {
       qualificationCourseService.levelUpTrainer(id);
    }
}


