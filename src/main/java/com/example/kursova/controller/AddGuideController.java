package com.example.kursova.controller;

import com.example.kursova.dao.GuideDAO;
import com.example.kursova.model.Guide;
import com.example.kursova.model.Tour;
import com.example.kursova.utils.DBUtil;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class AddGuideController {

    @FXML private TextField nameField;
    @FXML private TextField languageField;
    @FXML private TextField phoneField;
    private Guide guideToEdit;

    @FXML
    private Label errorLabel;

    private final GuideDAO guideDAO = new GuideDAO();

    @FXML
    private TextField searchField;

    @FXML
    private void addGuide() {
        String name = nameField.getText();
        String language = languageField.getText();
        String phone = phoneField.getText();

        Stage stage = (Stage) errorLabel.getScene().getWindow();

        if (name.isEmpty() || language.isEmpty()) {
            errorLabel.setText("Заповніть всі обов’язкові поля (ім’я та мова)");
            return;
        }

        Guide guide = new Guide(
                guideToEdit != null ? guideToEdit.getId() : 0,
                name, language, phone
        );

        if (guideToEdit == null) {
            guideDAO.addGuide(guide);
            System.out.println("✅ Гід доданий: " + guide);
        } else {
            guideDAO.updateGuide(guide);
            System.out.println("✅ Гід оновлений: " + guide);
        }

        stage.close();
    }


    public void setGuideToEdit(Guide guide) {
        this.guideToEdit = guide;

        // Попередньо заповнити поля форми
        if (guide != null) {
            nameField.setText(guide.getName());
            languageField.setText(guide.getLanguage());
            phoneField.setText(guide.getPhone());
        }
    }
}
