package com.mvl.services;

import com.mvl.models.Trainer;
import com.mvl.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class QualificationCourseService {
    private final TrainerRepository trainerRepository;

    @Autowired
    public QualificationCourseService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Transactional
    public void levelUpTrainer(UUID id) {
        trainerRepository.findById(id).get()
                .setSkillLevel(trainerRepository.findById(id).get()
                .getSkillLevel() + 1);
    }
}
