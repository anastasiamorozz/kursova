package com.example.kursova.controller;

import com.example.kursova.dao.TourDAO;
import com.example.kursova.model.Tour;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class TourListController {

    @FXML
    private TableView<Tour> tourTable;

    @FXML
    private TableColumn<Tour, String> titleColumn;

    @FXML
    private TableColumn<Tour, String> hotelColumn;

    @FXML
    private TableColumn<Tour, String> tourTypeColumn;

    @FXML
    private TableColumn<Tour, String> transportColumn;

    @FXML
    private TableColumn<Tour, String> mealTypeColumn;

    @FXML
    private TableColumn<Tour, Integer> daysColumn;

    @FXML
    private TableColumn<Tour, Double> priceColumn;

    @FXML
    private TableColumn<Tour, String> languageColumn;

    @FXML
    private TableColumn<Tour, String> guideColumn;

    @FXML
    private void handleAddTour() {
        openWindow("/com/example/kursova/form/AddTour.fxml", "Додати тур");
    }

    @FXML
    private void handleAddGuide() {
        openWindow("/com/example/kursova/form/AddGuide.fxml", "Додати гіда");
    }

    @FXML
    private void handleAddHotel() {
        openWindow("/com/example/kursova/form/AddHotel.fxml", "Додати готель");
    }

    private void openWindow(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.err.println("❌ Помилка відкриття вікна: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        System.out.println("Контролер ініціалізовано успішно!");

        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        hotelColumn.setCellValueFactory(new PropertyValueFactory<>("hotel"));
        tourTypeColumn.setCellValueFactory(new PropertyValueFactory<>("tourType"));
        transportColumn.setCellValueFactory(new PropertyValueFactory<>("transport"));
        mealTypeColumn.setCellValueFactory(new PropertyValueFactory<>("mealType"));
        daysColumn.setCellValueFactory(new PropertyValueFactory<>("days"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        languageColumn.setCellValueFactory(new PropertyValueFactory<>("language"));
        guideColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getGuide().toString()));


        tourTable.getItems().addAll(new TourDAO().getAllTours());
    }
}
