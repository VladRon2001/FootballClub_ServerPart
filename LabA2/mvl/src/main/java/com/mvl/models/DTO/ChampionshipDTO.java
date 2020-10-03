package com.mvl.models.DTO;

import com.mvl.models.FootballClub;

import java.util.List;

public class ChampionshipDTO {
    private List<FootballClub> participants;
    private List<Integer> scores;

    public List<FootballClub> getParticipants() {
        return participants;
    }

    public List<Integer> getScores() {
        return scores;
    }

    public void setScores(List<Integer> scores) {
        this.scores = scores;
    }

    public void setParticipants(List<FootballClub> participants) {
        this.participants = participants;
    }
}
