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

    @FXML
    private Label errorLabel;

    private final HotelDAO hotelDAO = new HotelDAO();

    @FXML
    private void addHotel() {
        String name = nameField.getText();
        String starsStr = starsField.getText();

        Stage stage = (Stage) errorLabel.getScene().getWindow();

        if (!name.isEmpty() && !starsStr.isEmpty()) {
            try {
                int stars = Integer.parseInt(starsStr);
                if (stars < 1 || stars > 5) {
                    System.out.println("Кількість зірок повинна бути від 1 до 5.");
                    return;
                }
                Hotel hotel = new Hotel(name, stars);
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
