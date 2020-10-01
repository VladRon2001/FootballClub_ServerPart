package com.mvl.models;

import javax.persistence.*;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Entity
public class Championship {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToMany(targetEntity = FootballPlayer.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn
    private List<FootballClub> participants;

    @Column
    @ElementCollection(targetClass=Integer.class)
    private List<Integer> scores;

    public Championship(List<FootballClub> participants) {
        this.participants = participants;

        for(int i = 0; i < participants.size(); ++i) {
            scores.add(0);
        }
    }


    private void playGame(FootballClub participantOne, FootballClub participantTwo,
                          int indexFirstClub, int indexSecondClub) {
        final Random random = new Random();
        int diffrenceFootballClub = participantOne.calculateStrongTeam()
                - participantTwo.calculateStrongTeam();

        int resultGame = diffrenceFootballClub + random.nextInt(30) - random.nextInt(30);

        if (resultGame > 10) {
            scores.set(indexFirstClub, scores.get(indexFirstClub) + 3);
        }
        else if (resultGame < -10) {
            scores.set(indexSecondClub, scores.get(indexSecondClub) + 3);
        }
        else {
            scores.set(indexFirstClub, scores.get(indexFirstClub) + 1);
            scores.set(indexSecondClub, scores.get(indexSecondClub) + 1);
        }
    }

    public void playRound() {
        for(int i = 0; i < participants.size(); ++i) {
            for(int j = i + 1; j < participants.size(); ++j) {
                playGame(participants.get(i), participants.get(j), i, j);
            }
        }
    }

    @Override
    public String toString() {
        String participantsToString = "";

        for (int i = 0; i < participants.size(); ++i) {
            participantsToString += participants.get(i).getClubName() + scores.get(i) + "\n";
        }

        return participantsToString;
    }
}
