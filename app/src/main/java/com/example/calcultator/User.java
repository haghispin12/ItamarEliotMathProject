package com.example.calcultator;


import android.net.Uri;

public class User {
    private String name;
    private int points;

    private int rate;

    private Uri uri;
    private String id;

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

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public String getId(String id){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }


}
