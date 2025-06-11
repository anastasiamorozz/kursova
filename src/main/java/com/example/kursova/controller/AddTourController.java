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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddTourController {

    @FXML
    TextField titleField;
    @FXML
    ComboBox<TourType> tourTypeCombo;
    @FXML
    ComboBox<TransportType> transportCombo;
    @FXML
    ComboBox<MealType> mealTypeCombo;
    @FXML
    TextField daysField;
    @FXML
    TextField priceField;
    @FXML
    ComboBox<Hotel> hotelCombo;
    @FXML
    ComboBox<Guide> guideCombo;
    @FXML
    Label errorLabel;

    private final TourDAO tourDAO = new TourDAO();
    private final HotelDAO hotelDAO = new HotelDAO();
    private final GuideDAO guideDAO = new GuideDAO();

    private Tour tourToEdit = null;

    @FXML
    private void initialize() {
        // tourTypeCombo.getItems().setAll(TourType.values());
        // transportCombo.getItems().setAll(TransportType.values());
        // mealTypeCombo.getItems().setAll(MealType.values());
        // hotelCombo.getItems().setAll(hotelDAO.getAllHotels());
        // guideCombo.getItems().setAll(guideDAO.getAllGuides());

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

    @FXML void handleSaveTour() {
        String title = titleField.getText();
        TourType tourType = tourTypeCombo.getValue();
        TransportType transport = transportCombo.getValue();
        MealType mealType = mealTypeCombo.getValue();
        String daysStr = daysField.getText();
        String priceStr = priceField.getText();
        Hotel hotel = hotelCombo.getValue();
        Guide guide = guideCombo.getValue();

        Stage stage = (Stage) errorLabel.getScene().getWindow();

        if (title.isEmpty()) {
            errorLabel.setText("Поле 'Назва' не заповнене");
            return;
        } else if (tourType == null) {
            errorLabel.setText("Поле 'Тип туру' не заповнене");
            return;
        } else if (transport == null) {
            errorLabel.setText("Поле 'Транспорт' не заповнене");
            return;
        } else if (mealType == null) {
            errorLabel.setText("Поле 'Тип харчування' не заповнене");
            return;
        } else if (daysStr.isEmpty()) {
            errorLabel.setText("Поле 'Дні' не заповнене");
            return;
        } else if (priceStr.isEmpty()) {
            errorLabel.setText("Поле 'Ціна' не заповнене");
            return;
        } else if (hotel == null) {
            errorLabel.setText("Поле 'Готель' не заповнене");
            return;
        } else if (guide == null) {
            errorLabel.setText("Поле 'Гід' не заповнене");
            return;
        }

        // Якщо всі перевірки пройшли, можна виконувати дії для збереження або додавання
        // даних

        try {
            int days = Integer.parseInt(daysStr);
            double price = Double.parseDouble(priceStr);

            // Якщо редагуємо
            if (tourToEdit != null) {
                tourToEdit.setTitle(title);
                tourToEdit.setTourType(tourType);
                tourToEdit.setTransport(transport);
                tourToEdit.setMealType(mealType);
                tourToEdit.setDays(days);
                tourToEdit.setPrice(price);
                tourToEdit.setHotel(hotel);
                tourToEdit.setGuide(guide);

                tourDAO.updateTour(tourToEdit); // оновити в БД
            } else {
                Tour newTour = new Tour(0, title, tourType, transport, mealType, days, price, hotel,
                        TourLanguage.UKRAINIAN, "", guide);
                tourDAO.addTour(newTour); // додати новий тур
            }

            stage.close();

        } catch (NumberFormatException e) {
            errorLabel.setText("Некоректна кількість днів або ціна");
        }
    }

    public void setTourToEdit(Tour tour) {
        this.tourToEdit = tour;

        // Заповнити поля значеннями
        titleField.setText(tour.getTitle());
        tourTypeCombo.setValue(tour.getTourType());
        transportCombo.setValue(tour.getTransport());
        mealTypeCombo.setValue(tour.getMealType());
        daysField.setText(String.valueOf(tour.getDays()));
        priceField.setText(String.valueOf(tour.getPrice()));
        hotelCombo.setValue(tour.getHotel());
        guideCombo.setValue(tour.getGuide());
    }

}
