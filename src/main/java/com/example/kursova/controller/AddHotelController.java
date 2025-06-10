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

        // Перевірка, чи заповнені всі поля
        if (!name.isEmpty() && !starsStr.isEmpty() && !country.isEmpty() && !city.isEmpty() && !address.isEmpty() && !phone.isEmpty() && !email.isEmpty()) {
            try {
                int stars = Integer.parseInt(starsStr);
                if (stars < 1 || stars > 5) {
                    System.out.println("Кількість зірок повинна бути від 1 до 5.");
                    return;
                }

                // Створення об'єкта Hotel
                Hotel hotel = new Hotel(name, stars, country, city, address, phone, email);

                // Додавання готелю до бази
                hotelDAO.addHotel(hotel);
                stage.close();
                System.out.println("Готель доданий: " + hotel);
            } catch (NumberFormatException e) {
                System.out.println("Будь ласка, введіть коректне число для зірок.");
            }
        } else {
            System.out.println("Будь ласка, заповніть всі поля.");
            errorLabel.setText("Заповніть всі поля");
        }
    }
}
