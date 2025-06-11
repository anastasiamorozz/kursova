package com.example.kursova.model;

import com.example.kursova.enums.*;

import java.util.Objects;

public class Tour {
    private int id;
    private String title;
    private TourType tourType;
    private TransportType transport;
    private MealType mealType;
    private int days;
    private double price;
    private Hotel hotel;
    private TourLanguage language;
    private String description;
    private Guide guide;

    public Tour(int id, String title, TourType tourType, TransportType transport, MealType mealType,
            int days, double price, Hotel hotel, TourLanguage language, String description, Guide guide) {
        this.id = id;
        this.title = title;
        this.tourType = tourType;
        this.transport = transport;
        this.mealType = mealType;
        this.days = days;
        this.price = price;
        this.hotel = hotel;
        this.language = language;
        this.description = description;
        this.guide = guide;
    }

    // Геттери та сеттери
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TourType getTourType() {
        return tourType;
    }

    public void setTourType(TourType tourType) {
        this.tourType = tourType;
    }

    public TransportType getTransport() {
        return transport;
    }

    public void setTransport(TransportType transport) {
        this.transport = transport;
    }

    public MealType getMealType() {
        return mealType;
    }

    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public TourLanguage getLanguage() {
        return language;
    }

    public void setLanguage(TourLanguage language) {
        this.language = language;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Guide getGuide() {
        return guide;
    }

    public void setGuide(Guide guide) {
        this.guide = guide;
    }

    @Override
    public String toString() {
        return title + " (" + tourType + ", " + days + " днів, " + price + "₴, гід: " + guide + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Tour tour = (Tour) o;
        return id == tour.id &&
                Objects.equals(title, tour.title) &&
                tourType == tour.tourType &&
                transport == tour.transport &&
                mealType == tour.mealType &&
                days == tour.days &&
                Double.compare(tour.price, price) == 0 &&
                Objects.equals(hotel, tour.hotel) &&
                language == tour.language &&
                Objects.equals(description, tour.description) &&
                Objects.equals(guide, tour.guide);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, tourType, transport, mealType, days, price, hotel, language, description, guide);
    }

    public boolean matchesSearch(String query) {
        String lower = query.toLowerCase();
        return title.toLowerCase().contains(lower)
                || hotel.getName().toLowerCase().contains(lower)
                || description.toLowerCase().contains(lower);
    }
}
