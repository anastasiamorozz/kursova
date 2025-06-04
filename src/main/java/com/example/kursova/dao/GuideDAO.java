package com.example.kursova.dao;

import com.example.kursova.model.Guide;
import com.example.kursova.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GuideDAO {
    public void addGuide(Guide guide) {
        String sql = "INSERT INTO guides (name, language, phone) VALUES (?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, guide.getName());
            stmt.setString(2, guide.getLanguage());
            stmt.setString(3, guide.getPhone());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Помилка додавання гіда: " + e.getMessage());
        }
    }

    public List<Guide> getAllGuides() {
        List<Guide> guides = new ArrayList<>();
        String sql = "SELECT * FROM guides";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Guide guide = new Guide(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("language"),
                        rs.getString("phone")
                );
                guides.add(guide);
            }

        } catch (SQLException e) {
            System.err.println("❌ Помилка отримання гідів: " + e.getMessage());
        }

        return guides;
    }

    public Guide getGuideById(int id) {
        String sql = "SELECT * FROM guides WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Guide(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("language"),
                        rs.getString("phone")
                );
            }

        } catch (SQLException e) {
            System.err.println("❌ Помилка пошуку гіда: " + e.getMessage());
        }
        return null;
    }
}
