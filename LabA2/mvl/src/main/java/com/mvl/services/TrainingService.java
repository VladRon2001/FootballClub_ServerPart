package com.mvl.services;

import com.mvl.models.FootballPlayer;
import com.mvl.repository.FootballClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class TrainingService {
    final private FootballClubRepository footballClubRepository;

    @Autowired
    public TrainingService(FootballClubRepository footballClubRepository) {
        this.footballClubRepository = footballClubRepository;
    }

    @Transactional
    public void train(UUID clubId) {
        for (FootballPlayer footballPlayer
                :footballClubRepository.findById(clubId).get().getFootballTeam()) {
            double newRatingScore = footballPlayer.getRatingScore()
                    + (30-footballPlayer.getAge())*0.1
                    * footballClubRepository.findById(clubId)
                    .get().getTrainer().getSkillLevel();

            footballPlayer.setRatingScore((int)newRatingScore);
        }
    }
}
