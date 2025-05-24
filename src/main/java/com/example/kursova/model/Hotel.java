package com.example.kursova.model;

public class Hotel {
    private String name;
    private int stars;

    public Hotel(String name, int stars) {
        this.name = name;
        this.stars = stars;
    }

    public String getName() {
        return name;
    }

    public int getStars() {
        return stars;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        return name + " (" + stars + "★)";
    }
}
