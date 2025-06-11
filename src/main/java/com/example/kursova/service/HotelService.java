package com.example.kursova.service;

import com.example.kursova.dao.HotelDAO;
import com.example.kursova.model.Hotel;

import java.util.List;

public class HotelService {
    private HotelDAO hotelDAO;

    public HotelService() {
        this.hotelDAO = new HotelDAO();
    }

    public void setHotelDAO(HotelDAO hotelDAO) {
        this.hotelDAO = hotelDAO;
    }

    public List<Hotel> getAllHotels() {
        return hotelDAO.getAllHotels();
    }

    public void addHotel(Hotel hotel) {
        hotelDAO.addHotel(hotel);
    }

    public void updateHotel(Hotel hotel) {
        hotelDAO.updateHotel(hotel);
    }

    public void deleteHotel(String hotelName) {
        hotelDAO.deleteHotel(hotelName);
    }
}