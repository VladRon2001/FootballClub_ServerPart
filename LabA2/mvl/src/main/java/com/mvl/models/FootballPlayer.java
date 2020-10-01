package com.mvl.models;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class FootballPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    private final String name;
    private final int age;
    private int ratingScore;
    private final String position;


    public FootballPlayer(String name, int age, int ratingScore, String position) {
        this.age = age;
        this.name = name;
        this.ratingScore = ratingScore;
        this.position = position;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getRatingScore() {
        return ratingScore;
    }

    public void setRatingScore(int ratingScore) {
        this.ratingScore = ratingScore;
    }

    public int calculateCost() {
        return 5000*(45-age)*ratingScore;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return name + " "
                + ratingScore + " "
                + position + " "
                + age;
    }
}
