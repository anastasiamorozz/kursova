package com.example.kursova.service;

import com.example.kursova.dao.HotelDAO;
import com.example.kursova.model.Hotel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class HotelServiceTest {

    @Mock
    private HotelDAO hotelDAO;

    private HotelService hotelService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        hotelService = new HotelService();
        hotelService.setHotelDAO(hotelDAO);
    }

    @Test
    void testGetAllHotels() {
        List<Hotel> expectedHotels = Arrays.asList(
                new Hotel("Hotel 1", 4, "Country 1", "City 1", "Address 1", "123456789", "hotel1@example.com"),
                new Hotel("Hotel 2", 5, "Country 2", "City 2", "Address 2", "987654321", "hotel2@example.com"));
        when(hotelDAO.getAllHotels()).thenReturn(expectedHotels);
        List<Hotel> actualHotels = hotelService.getAllHotels();
        assertEquals(expectedHotels, actualHotels);
    }

    @Test
    void testAddHotel() {
        Hotel hotel = new Hotel("Hotel 1", 4, "Country 1", "City 1", "Address 1", "123456789", "hotel1@example.com");
        hotelService.addHotel(hotel);
        verify(hotelDAO, times(1)).addHotel(hotel);
    }

    @Test
    void testUpdateHotel() {
        Hotel hotel = new Hotel("Hotel 1", 4, "Country 1", "City 1", "Address 1", "123456789", "hotel1@example.com");
        hotelService.updateHotel(hotel);
        verify(hotelDAO, times(1)).updateHotel(hotel);
    }

    @Test
    void testDeleteHotel() {
        String hotelName = "Hotel 1";
        hotelService.deleteHotel(hotelName);
        verify(hotelDAO, times(1)).deleteHotel(hotelName);
    }
}