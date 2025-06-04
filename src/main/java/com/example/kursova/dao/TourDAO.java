package com.example.kursova.dao;

import com.example.kursova.enums.*;
import com.example.kursova.model.*;
import com.example.kursova.utils.DBUtil;

import java.sql.*;
import java.util.*;

public class TourDAO {
    private final GuideDAO guideDAO = new GuideDAO();
    private final HotelDAO hotelDAO = new HotelDAO();

    public void addTour(Tour tour) {
        // Обов'язково зберегти готель і гіда перед додаванням туру
        hotelDAO.addHotel(tour.getHotel());
        guideDAO.addGuide(tour.getGuide());

        String sql = """
            INSERT INTO tours (
                title, tour_type, transport, meal_type, days, price,
                hotel_name, language, description, guide_id
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tour.getTitle());
            stmt.setString(2, tour.getTourType().name());
            stmt.setString(3, tour.getTransport().name());
            stmt.setString(4, tour.getMealType().name());
            stmt.setInt(5, tour.getDays());
            stmt.setDouble(6, tour.getPrice());
            stmt.setString(7, tour.getHotel().getName());
            stmt.setString(8, tour.getLanguage().name());
            stmt.setString(9, tour.getDescription());
            stmt.setInt(10, tour.getGuide().getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Помилка додавання туру: " + e.getMessage());
        }
    }

    public List<Tour> getAllTours() {
        List<Tour> tours = new ArrayList<>();
        String sql = "SELECT * FROM tours";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Hotel hotel = hotelDAO.getHotelByName(rs.getString("hotel_name"));
                Guide guide = guideDAO.getGuideById(rs.getInt("guide_id"));

                Tour tour = new Tour(
                        rs.getInt("id"),
                        rs.getString("title"),
                        TourType.valueOf(rs.getString("tour_type")),
                        TransportType.valueOf(rs.getString("transport")),
                        MealType.valueOf(rs.getString("meal_type")),
                        rs.getInt("days"),
                        rs.getDouble("price"),
                        hotel,
                        TourLanguage.valueOf(rs.getString("language")),
                        rs.getString("description"),
                        guide
                );

                tours.add(tour);
            }

        } catch (SQLException e) {
            System.err.println("❌ Помилка отримання турів: " + e.getMessage());
        }

        return tours;
    }
}
