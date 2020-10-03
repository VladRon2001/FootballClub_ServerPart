package com.mvl.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class FootballClub {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne
    private Trainer trainer;

    @OneToMany(targetEntity = FootballPlayer.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn
    private List<FootballPlayer> footballTeam;

    private String clubName;
    private int budget;

    public FootballClub(String clubName, List<FootballPlayer> footballTeam, Trainer trainer, int budget) {
        this.clubName = clubName;
        this.budget = budget;
        this.footballTeam = footballTeam;
        this.trainer = trainer;
    }

    public FootballClub() {

    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getClubName() {
        return clubName;
    }

    public List<FootballPlayer> getFootballTeam() {
        return footballTeam;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void addPlayerToFootballTeam(FootballPlayer footballPlayer) {
        footballTeam.add(footballPlayer);
    }

    public void deletePlayerToFootballTeam(FootballPlayer footballPlayer) {
        footballTeam.remove(footballPlayer);
    }

    public String showFootballClub() {
        String trainerToString = "Trainer\n" + trainer.toString();

        String listPlayersInClub = "\nPlayers\n";
        for (FootballPlayer footballPlayer: footballTeam) {
            listPlayersInClub += footballPlayer.toString();
            listPlayersInClub += "\n";
        }

        return trainerToString+listPlayersInClub;
    }

    public int calculateStrongTeam() {
        int sumRatingScore = 0;

        for(FootballPlayer footballPlayer:footballTeam) {
            sumRatingScore += footballPlayer.getRatingScore();
        }

        return sumRatingScore/footballTeam.size();
    }
}
