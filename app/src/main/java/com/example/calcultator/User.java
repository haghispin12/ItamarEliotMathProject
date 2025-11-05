package com.example.calcultator;


public class User {
    private String name;
    private int points;

    private int rate;

    public User(String name) {
        this.name = name;

    }

    public String setname(String name){
        return name;
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

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }


}
