package com.example.calcultator;


public class User {
    private String name;
    private int points;

    public User(String name) {
        this.name = name;

    }


    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addPoints(int points) {
        this.points += points;
    }


}
