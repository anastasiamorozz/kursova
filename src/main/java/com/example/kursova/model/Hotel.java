package com.example.kursova.model;

public class Hotel {
    private String name;
    private int stars;
    private String country;
    private String city;
    private String address;
    private String phone;
    private String email;

    public Hotel(String name, int stars, String country, String city, String address, String phone, String email) {
        this.name = name;
        this.stars = stars;
        this.country = country;
        this.city = city;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public int getStars() {
        return stars;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return name + " (" + stars + "★), " + city + ", " + country;
    }
}
