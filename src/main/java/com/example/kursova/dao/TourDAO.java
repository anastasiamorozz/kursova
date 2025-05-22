package com.example.kursova.dao;

import com.example.kursova.enums.*;
import com.example.kursova.model.*;

import java.util.*;
import java.util.stream.Collectors;

public class TourDAO {
    private final List<Tour> tours;

    public TourDAO() {
        this.tours = new ArrayList<>();

        tours.add(new Tour(
                1,
                "Сонячна Болгарія",
                TourType.VACATION,
                TransportType.PLANE,
                MealType.ALL_INCLUSIVE,
                7,
                12000,
                "Hotel Sunny Paradise",
                TourLanguage.UKRAINIAN,
                "Незабутній відпочинок на узбережжі Чорного моря."
        ));

        tours.add(new Tour(
                2,
                "Екскурсія до Риму",
                TourType.EXCURSION,
                TransportType.PLANE,
                MealType.BREAKFAST,
                5,
                14500,
                "Hotel Bella Roma",
                TourLanguage.ENGLISH,
                "Поринь у атмосферу стародавнього міста!"
        ));

        tours.add(new Tour(
                3,
                "Медичний тур до Карлових Вар",
                TourType.MEDICAL,
                TransportType.BUS,
                MealType.BREAKFAST,
                10,
                18000,
                "Thermal Spa Resort",
                TourLanguage.GERMAN,
                "Лікування та релакс у Чехії"
        ));
    }

    public List<Tour> getAllTours() {
        return new ArrayList<>(tours);
    }

    public void addTour(Tour tour) {
        tours.add(tour);
    }

    public void deleteTour(int id) {
        tours.removeIf(t -> t.getId() == id);
    }

    public void updateTour(Tour updatedTour) {
        for (int i = 0; i < tours.size(); i++) {
            if (tours.get(i).getId() == updatedTour.getId()) {
                tours.set(i, updatedTour);
                return;
            }
        }
    }

    public List<Tour> searchTours(String query) {
        return tours.stream()
                .filter(tour -> tour.matchesSearch(query))
                .collect(Collectors.toList());
    }

    public List<Tour> getFilteredTours(TourFilter filter) {
        return tours.stream()
                .filter(filter::matches)
                .collect(Collectors.toList());
    }

    public Optional<Tour> getTourById(int id) {
        return tours.stream()
                .filter(t -> t.getId() == id)
                .findFirst();
    }
}
