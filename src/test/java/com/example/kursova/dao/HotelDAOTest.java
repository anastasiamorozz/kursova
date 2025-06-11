package com.example.kursova.dao;

import com.example.kursova.model.Hotel;
import com.example.kursova.utils.DBUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HotelDAOTest {

    private HotelDAO hotelDAO;

    @BeforeAll
    static void setupDatabase() throws Exception {
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
        }
    }

    @BeforeEach
    void setUp() throws Exception {
        hotelDAO = new HotelDAO();
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM hotels");
        }
    }

    @Test
    void testAddAndGetHotelByName() {
        Hotel hotel = new Hotel("Hilton", 5, "USA", "NYC", "5th Ave", "123456789", "hilton@example.com");
        hotelDAO.addHotel(hotel);

        Hotel fetched = hotelDAO.getHotelByName("Hilton");
        assertNotNull(fetched);
        assertEquals("Hilton", fetched.getName());
        assertEquals(5, fetched.getStars());
    }

    @Test
    void testGetAllHotels() {
        Hotel hotel1 = new Hotel("Marriott", 4, "USA", "Boston", "Main St", "987654321", "marriott@example.com");
        Hotel hotel2 = new Hotel("Ibis", 3, "France", "Paris", "Rue de Paris", "111111111", "ibis@example.com");
        hotelDAO.addHotel(hotel1);
        hotelDAO.addHotel(hotel2);

        List<Hotel> hotels = hotelDAO.getAllHotels();
        assertEquals(2, hotels.size());
    }

    @Test
    void testUpdateHotel() {
        Hotel hotel = new Hotel("Radisson", 4, "Germany", "Berlin", "Strasse", "222222222", "radisson@example.com");
        hotelDAO.addHotel(hotel);

        hotel.setStars(5);
        hotel.setCountry("Austria");
        hotel.setCity("Vienna");
        hotelDAO.updateHotel(hotel);

        Hotel updated = hotelDAO.getHotelByName("Radisson");
        assertNotNull(updated);
        assertEquals(5, updated.getStars());
        assertEquals("Austria", updated.getCountry());
        assertEquals("Vienna", updated.getCity());
    }

    @Test
    void testDeleteHotel() {
        Hotel hotel = new Hotel("DeleteMe", 3, "Spain", "Madrid", "Gran Via", "333333333", "deleteme@example.com");
        hotelDAO.addHotel(hotel);

        hotelDAO.deleteHotel("DeleteMe");

        Hotel deleted = hotelDAO.getHotelByName("DeleteMe");
        assertNull(deleted);
    }
}
