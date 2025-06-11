package com.example.kursova.service;

import com.example.kursova.dao.TourDAO;
import com.example.kursova.model.Tour;
import com.example.kursova.model.TourFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TourServiceTest {

    @Mock
    private TourDAO tourDAO;

    private TourService tourService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        tourService = new TourService();
        tourService.setTourDAO(tourDAO);
    }

    @Test
    void testGetAllTours() {
        Tour tour1 = new Tour(1, "Tour 1", null, null, null, 5, 100.0, null, null, "Description 1", null);
        Tour tour2 = new Tour(2, "Tour 2", null, null, null, 7, 200.0, null, null, "Description 2", null);
        List<Tour> expectedTours = Arrays.asList(tour1, tour2);
        when(tourDAO.getAllTours()).thenReturn(expectedTours);
        List<Tour> actualTours = tourService.getAllTours();
        assertEquals(expectedTours, actualTours);
    }

    @Test
    void testFilterTours() {
        TourFilter filter = new TourFilter();
        Tour tour = new Tour(1, "Tour 1", null, null, null, 5, 100.0, null, null, "Description 1", null);
        List<Tour> expectedTours = Arrays.asList(tour);
        when(tourDAO.getFilteredTours(filter)).thenReturn(expectedTours);
        List<Tour> actualTours = tourService.filterTours(filter);
        assertEquals(expectedTours, actualTours);
    }

    @Test
    void testSearchTours() {
        String keyword = "test";
        Tour tour = new Tour(1, "Test Tour", null, null, null, 5, 100.0, null, null, "Description 1", null);
        List<Tour> expectedTours = Arrays.asList(tour);
        when(tourDAO.getAllTours()).thenReturn(expectedTours);
        List<Tour> actualTours = tourService.searchTours(keyword);
        assertEquals(expectedTours, actualTours);
    }
}