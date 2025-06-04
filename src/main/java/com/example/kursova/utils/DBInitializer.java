package com.example.kursova.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBInitializer {
    public static void init() {
        String createGuidesTable = """
            CREATE TABLE IF NOT EXISTS guides (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                language TEXT NOT NULL,
                phone TEXT
            );
        """;

        String createHotelsTable = """
            CREATE TABLE IF NOT EXISTS hotels (
                name TEXT PRIMARY KEY,
                stars INTEGER NOT NULL
            );
        """;

        String createToursTable = """
            CREATE TABLE IF NOT EXISTS tours (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT NOT NULL,
                tour_type TEXT NOT NULL,
                transport TEXT NOT NULL,
                meal_type TEXT NOT NULL,
                days INTEGER,
                price REAL,
                hotel_name TEXT,
                language TEXT,
                description TEXT,
                guide_id INTEGER,
                FOREIGN KEY (hotel_name) REFERENCES hotels(name),
                FOREIGN KEY (guide_id) REFERENCES guides(id)
            );
        """;

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(createGuidesTable);
            stmt.execute(createHotelsTable);
            stmt.execute(createToursTable);

            System.out.println("✅ База даних ініціалізована (guides, hotels, tours).");

        } catch (SQLException e) {
            System.err.println("❌ Помилка ініціалізації БД: " + e.getMessage());
        }
    }
}
