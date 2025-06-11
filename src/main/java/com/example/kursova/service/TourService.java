package com.example.kursova.service;

import com.example.kursova.dao.TourDAO;
import com.example.kursova.model.Tour;
import com.example.kursova.model.TourFilter;

import java.util.List;
import java.util.stream.Collectors;

public class TourService {
    private TourDAO tourDAO;

    public TourService() {
        this.tourDAO = new TourDAO();
    }

    public void setTourDAO(TourDAO tourDAO) {
        this.tourDAO = tourDAO;
    }

    public List<Tour> getAllTours() {
        return tourDAO.getAllTours();
    }

    public List<Tour> filterTours(TourFilter filter) {
        return tourDAO.getFilteredTours(filter);
    }

    public List<Tour> searchTours(String keyword) {
        return tourDAO.getAllTours().stream()
                .filter(tour -> tour.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }
}