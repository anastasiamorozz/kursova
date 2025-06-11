package com.example.kursova.dao;

import com.example.kursova.enums.*;
import com.example.kursova.model.*;
import com.example.kursova.utils.DBUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TourDAOTest {

    private TourDAO tourDAO;
    private HotelDAO hotelDAO;
    private GuideDAO guideDAO;

    @BeforeAll
    static void setupTables() throws Exception {
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS hotels (
                    name TEXT PRIMARY KEY,
                    stars INTEGER,
                    country TEXT,
                    city TEXT,
                    address TEXT,
                    phone TEXT,
                    email TEXT
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS guides (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT,
                    language TEXT,
                    phone TEXT
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS tours (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    title TEXT,
                    tour_type TEXT,
                    transport TEXT,
                    meal_type TEXT,
                    days INTEGER,
                    price REAL,
                    hotel_name TEXT,
                    language TEXT,
                    description TEXT,
                    guide_id INTEGER,
                    FOREIGN KEY (hotel_name) REFERENCES hotels(name),
                    FOREIGN KEY (guide_id) REFERENCES guides(id)
                )
            """);
        }
    }

    @BeforeEach
    void init() throws Exception {
        tourDAO = new TourDAO();
        hotelDAO = new HotelDAO();
        guideDAO = new GuideDAO();

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM tours");
            stmt.execute("DELETE FROM hotels");
            stmt.execute("DELETE FROM guides");
        }
    }

    private Hotel createTestHotel() {
        Hotel hotel = new Hotel("TestHotel", 4, "Ukraine", "Lviv", "Main St", "123456789", "hotel@test.com");
        hotelDAO.addHotel(hotel);
        return hotel;
    }

    private Guide createTestGuide() {
        Guide guide = new Guide(0, "John Doe", "ENGLISH", "555-123");
        guideDAO.addGuide(guide);
        // Get ID from DB
        return guideDAO.getAllGuides().get(0);
    }

    @Test
    void testAddAndGetTour() {
        Hotel hotel = createTestHotel();
        Guide guide = createTestGuide();

        Tour tour = new Tour(0, "Amazing Tour", TourType.EXCURSION, TransportType.BUS, MealType.BREAKFAST,
                5, 499.99, hotel, TourLanguage.ENGLISH, "Explore the mountains", guide);

        tourDAO.addTour(tour);

        List<Tour> tours = tourDAO.getAllTours();
        assertEquals(1, tours.size());

        Tour retrieved = tours.get(0);
        assertEquals("Amazing Tour", retrieved.getTitle());
        assertEquals(hotel.getName(), retrieved.getHotel().getName());
        assertEquals(guide.getName(), retrieved.getGuide().getName());
    }

    @Test
    void testUpdateTour() {
        Hotel hotel = createTestHotel();
        Guide guide = createTestGuide();

        Tour tour = new Tour(0, "City Tour", TourType.CRUISE, TransportType.BUS, MealType.ALL_INCLUSIVE,
                2, 299.99, hotel, TourLanguage.UKRAINIAN, "Discover the city", guide);

        tourDAO.addTour(tour);
        Tour stored = tourDAO.getAllTours().get(0);

        // Update
        stored.setTitle("Updated Tour");
        stored.setDays(3);
        stored.setPrice(399.99);
        tourDAO.updateTour(stored);

        Tour updated = tourDAO.getAllTours().get(0);
        assertEquals("Updated Tour", updated.getTitle());
        assertEquals(3, updated.getDays());
        assertEquals(399.99, updated.getPrice());
    }

    @Test
    void testGetFilteredTours() {
        Hotel hotel = createTestHotel();
        Guide guide = createTestGuide();

        Tour tour1 = new Tour(0, "Sea Tour", TourType.MEDICAL, TransportType.PLANE, MealType.BREAKFAST,
                7, 1000, hotel, TourLanguage.ENGLISH, "Relax at the sea", guide);
        Tour tour2 = new Tour(0, "Mountain Adventure", TourType.CRUISE, TransportType.BUS, MealType.BREAKFAST,
                3, 300, hotel, TourLanguage.UKRAINIAN, "Climb the peaks", guide);

        tourDAO.addTour(tour1);
        tourDAO.addTour(tour2);

        TourFilter filter = new TourFilter();
        filter.setMinDays(5);
        filter.setMaxPrice(800.0);

        List<Tour> filtered = tourDAO.getFilteredTours(filter);
        assertEquals(0, filtered.size());

        filter.setMaxPrice(1200.0);
        List<Tour> filtered2 = tourDAO.getFilteredTours(filter);
        assertEquals(1, filtered2.size());
        assertEquals("Sea Tour", filtered2.get(0).getTitle());
    }
}
