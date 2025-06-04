package com.example.kursova.controller;

import com.example.kursova.dao.GuideDAO;
import com.example.kursova.dao.HotelDAO;
import com.example.kursova.dao.TourDAO;
import com.example.kursova.enums.*;
import com.example.kursova.model.Guide;
import com.example.kursova.model.Hotel;
import com.example.kursova.model.Tour;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AddTourController {

    @FXML private TextField titleField;
    @FXML private ComboBox<TourType> tourTypeCombo;
    @FXML private ComboBox<TransportType> transportCombo;
    @FXML private ComboBox<MealType> mealTypeCombo;
    @FXML private TextField daysField;
    @FXML private TextField priceField;
    @FXML private ComboBox<Hotel> hotelCombo;
    @FXML private ComboBox<Guide> guideCombo;

    private final TourDAO tourDAO = new TourDAO();
    private final HotelDAO hotelDAO = new HotelDAO();
    private final GuideDAO guideDAO = new GuideDAO();

    @FXML
    private void initialize() {
//        tourTypeCombo.getItems().setAll(TourType.values());
//        transportCombo.getItems().setAll(TransportType.values());
//        mealTypeCombo.getItems().setAll(MealType.values());
//        hotelCombo.getItems().setAll(hotelDAO.getAllHotels());
//        guideCombo.getItems().setAll(guideDAO.getAllGuides());

        System.out.println("⚙ Ініціалізація AddTourController");

        try {
            tourTypeCombo.getItems().setAll(TourType.values());
            transportCombo.getItems().setAll(TransportType.values());
            mealTypeCombo.getItems().setAll(MealType.values());

            hotelCombo.getItems().setAll(hotelDAO.getAllHotels());
            guideCombo.getItems().setAll(guideDAO.getAllGuides());
        } catch (Exception e) {
            e.printStackTrace(); // ⛔ побачиш точну причину
        }
    }

    @FXML
    private void addTour() {
        String title = titleField.getText();
        TourType tourType = tourTypeCombo.getValue();
        TransportType transport = transportCombo.getValue();
        MealType mealType = mealTypeCombo.getValue();
        String daysStr = daysField.getText();
        String priceStr = priceField.getText();
        Hotel hotel = hotelCombo.getValue();
        Guide guide = guideCombo.getValue();

        if (title.isEmpty() || tourType == null || transport == null || mealType == null ||
                daysStr.isEmpty() || priceStr.isEmpty() || hotel == null || guide == null) {
            System.out.println("Будь ласка, заповніть всі поля.");
            return;
        }

        try {
            int days = Integer.parseInt(daysStr);
            double price = Double.parseDouble(priceStr);

            Tour tour = new Tour(0, title, tourType, transport, mealType, days, price, hotel, TourLanguage.UKRAINIAN, "", guide);
            tourDAO.addTour(tour);
            System.out.println("Тур доданий: " + tour);
        } catch (NumberFormatException e) {
            System.out.println("Будь ласка, введіть коректні значення для кількості днів та ціни.");
        }
    }
}
