package com.example.kursova.model;

import com.example.kursova.enums.*;

public class TourFilter {
    private TourType tourType;
    private TransportType transportType;
    private MealType mealType;
    private TourLanguage language;
    private Integer minDays;
    private Integer maxDays;
    private Double minPrice;
    private Double maxPrice;

    public TourFilter() {
        // Значення за замовчуванням — null, тобто необов'язкові
    }

    // Геттери та сеттери
    public TourType getTourType() { return tourType; }
    public void setTourType(TourType tourType) { this.tourType = tourType; }

    public TransportType getTransportType() { return transportType; }
    public void setTransportType(TransportType transportType) { this.transportType = transportType; }

    public MealType getMealType() { return mealType; }
    public void setMealType(MealType mealType) { this.mealType = mealType; }

    public TourLanguage getLanguage() { return language; }
    public void setLanguage(TourLanguage language) { this.language = language; }

    public Integer getMinDays() { return minDays; }
    public void setMinDays(Integer minDays) { this.minDays = minDays; }

    public Integer getMaxDays() { return maxDays; }
    public void setMaxDays(Integer maxDays) { this.maxDays = maxDays; }

    public Double getMinPrice() { return minPrice; }
    public void setMinPrice(Double minPrice) { this.minPrice = minPrice; }

    public Double getMaxPrice() { return maxPrice; }
    public void setMaxPrice(Double maxPrice) { this.maxPrice = maxPrice; }

    // Метод перевірки: чи підходить тур під фільтр
    public boolean matches(Tour tour) {
        if (tourType != null && tour.getTourType() != tourType) return false;
        if (transportType != null && tour.getTransport() != transportType) return false;
        if (mealType != null && tour.getMealType() != mealType) return false;
        if (language != null && tour.getLanguage() != language) return false;
        if (minDays != null && tour.getDays() < minDays) return false;
        if (maxDays != null && tour.getDays() > maxDays) return false;
        if (minPrice != null && tour.getPrice() < minPrice) return false;
        if (maxPrice != null && tour.getPrice() > maxPrice) return false;

        return true;
    }
}
