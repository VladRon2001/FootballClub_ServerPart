package com.mvl.models.DTO;

public class FootballPlayerDTO {
    private String name;
    private int age;
    private int ratingScore;
    private String position;

    public int getAge() {
        return age;
    }

    public int getRatingScore() {
        return ratingScore;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public void setRatingScore(int ratingScore) {
        this.ratingScore = ratingScore;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
