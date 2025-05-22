package com.example.kursova.model;

import com.example.kursova.enums.*;

public class Tour {
    private int id;
    private String title;
    private TourType tourType;
    private TransportType transport;
    private MealType mealType;
    private int days;
    private double price;
    private String hotelName;
    private TourLanguage language;
    private String description;

    public Tour(int id, String title, TourType tourType, TransportType transport, MealType mealType,
                int days, double price, String hotelName, TourLanguage language, String description) {
        this.id = id;
        this.title = title;
        this.tourType = tourType;
        this.transport = transport;
        this.mealType = mealType;
        this.days = days;
        this.price = price;
        this.hotelName = hotelName;
        this.language = language;
        this.description = description;
    }

    // Геттери та сеттери
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public TourType getTourType() { return tourType; }
    public void setTourType(TourType tourType) { this.tourType = tourType; }

    public TransportType getTransport() { return transport; }
    public void setTransport(TransportType transport) { this.transport = transport; }

    public MealType getMealType() { return mealType; }
    public void setMealType(MealType mealType) { this.mealType = mealType; }

    public int getDays() { return days; }
    public void setDays(int days) { this.days = days; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getHotelName() { return hotelName; }
    public void setHotelName(String hotelName) { this.hotelName = hotelName; }

    public TourLanguage getLanguage() { return language; }
    public void setLanguage(TourLanguage language) { this.language = language; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return title + " (" + tourType + ", " + days + " днів, " + price + "₴)";
    }

    public boolean matchesSearch(String query) {
        String lower = query.toLowerCase();
        return title.toLowerCase().contains(lower)
                || hotelName.toLowerCase().contains(lower)
                || description.toLowerCase().contains(lower);
    }
}
