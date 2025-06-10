package com.example.kursova.controller;

import com.example.kursova.dao.HotelDAO;
import com.example.kursova.model.Hotel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddHotelController {

    @FXML private TextField nameField;
    @FXML private TextField starsField;
    @FXML private TextField countryField;
    @FXML private TextField cityField;
    @FXML private TextField addressField;
    @FXML private TextField phoneField;
    @FXML private TextField emailField;

    @FXML private Label errorLabel;

    private final HotelDAO hotelDAO = new HotelDAO();

    private Hotel hotelToEdit;

    public void setHotelToEdit(Hotel hotel) {
        this.hotelToEdit = hotel;
        nameField.setText(hotel.getName());
        starsField.setText(String.valueOf(hotel.getStars()));
        countryField.setText(hotel.getCountry());
        cityField.setText(hotel.getCity());
        addressField.setText(hotel.getAddress());
        phoneField.setText(hotel.getPhone());
        emailField.setText(hotel.getEmail());
    }

    @FXML
    private void addHotel() {
        String name = nameField.getText();
        String starsStr = starsField.getText();
        String country = countryField.getText();
        String city = cityField.getText();
        String address = addressField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();

        Stage stage = (Stage) errorLabel.getScene().getWindow();

        if (!name.isEmpty() && !starsStr.isEmpty() && !country.isEmpty() &&
                !city.isEmpty() && !address.isEmpty() && !phone.isEmpty() && !email.isEmpty()) {
            try {
                int stars = Integer.parseInt(starsStr);
                if (stars < 1 || stars > 5) {
                    errorLabel.setText("Кількість зірок повинна бути від 1 до 5.");
                    return;
                }

                if (hotelToEdit != null) {
                    // Редагування
                    hotelToEdit.setName(name);
                    hotelToEdit.setStars(stars);
                    hotelToEdit.setCountry(country);
                    hotelToEdit.setCity(city);
                    hotelToEdit.setAddress(address);
                    hotelToEdit.setPhone(phone);
                    hotelToEdit.setEmail(email);

                    hotelDAO.updateHotel(hotelToEdit);
                    System.out.println("Готель оновлено: " + hotelToEdit);
                } else {
                    // Додавання нового
                    Hotel hotel = new Hotel(name, stars, country, city, address, phone, email);
                    hotelDAO.addHotel(hotel);
                    System.out.println("Готель додано: " + hotel);
                }

                stage.close();
            } catch (NumberFormatException e) {
                errorLabel.setText("Введіть коректне число для зірок.");
            }
        } else {
            errorLabel.setText("Заповніть всі поля.");
        }
    }

}
