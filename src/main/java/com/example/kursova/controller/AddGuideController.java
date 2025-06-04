package com.example.kursova.controller;

import com.example.kursova.dao.GuideDAO;
import com.example.kursova.model.Guide;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddGuideController {

    @FXML private TextField nameField;
    @FXML private TextField languageField;
    @FXML private TextField phoneField;

    @FXML
    private Label errorLabel;

    private final GuideDAO guideDAO = new GuideDAO();

    @FXML
    private void addGuide() {
        String name = nameField.getText();
        String language = languageField.getText();
        String phone = phoneField.getText();

        Stage stage = (Stage) errorLabel.getScene().getWindow();

        if (!name.isEmpty() && !language.isEmpty() && !phone.isEmpty()) {
            Guide guide = new Guide(0, name, language, phone);
            guideDAO.addGuide(guide);
            stage.close();
            System.out.println("Гід доданий: " + guide);
        } else {
            System.out.println("Будь ласка, заповніть всі поля.");
            errorLabel.setText("Заповніть всі поля");
        }
    }
}
