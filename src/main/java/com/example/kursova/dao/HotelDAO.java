package com.example.kursova.dao;

import com.example.kursova.model.Hotel;
import com.example.kursova.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelDAO {

    public void addHotel(Hotel hotel) {
        String sql = "INSERT OR IGNORE INTO hotels (name, stars, country, city, address, phone, email) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, hotel.getName());
            stmt.setInt(2, hotel.getStars());
            stmt.setString(3, hotel.getCountry());
            stmt.setString(4, hotel.getCity());
            stmt.setString(5, hotel.getAddress());
            stmt.setString(6, hotel.getPhone());
            stmt.setString(7, hotel.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Помилка додавання готелю: " + e.getMessage());
        }
    }

    public List<Hotel> getAllHotels() {
        List<Hotel> hotels = new ArrayList<>();
        String sql = "SELECT * FROM hotels";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                hotels.add(new Hotel(
                        rs.getString("name"),
                        rs.getInt("stars"),
                        rs.getString("country"),
                        rs.getString("city"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("email")
                ));
            }

        } catch (SQLException e) {
            System.err.println("❌ Помилка отримання готелів: " + e.getMessage());
        }

        return hotels;
    }

    public Hotel getHotelByName(String name) {
        String sql = "SELECT * FROM hotels WHERE name = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Hotel(
                        rs.getString("name"),
                        rs.getInt("stars"),
                        rs.getString("country"),
                        rs.getString("city"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("email")
                );
            }
        } catch (SQLException e) {
            System.err.println("❌ Помилка пошуку готелю: " + e.getMessage());
        }
        return null;
    }

    public void deleteHotel(String name) {
        String sql = "DELETE FROM hotels WHERE name = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Помилка видалення готелю: " + e.getMessage());
        }
    }

    public void updateHotel(Hotel hotel) {
        String sql = "UPDATE hotels SET stars = ?, country = ?, city = ? WHERE name = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, hotel.getStars());
            stmt.setString(2, hotel.getCountry());
            stmt.setString(3, hotel.getCity());
            stmt.setString(4, hotel.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Помилка оновлення готелю: " + e.getMessage());
        }
    }

}
