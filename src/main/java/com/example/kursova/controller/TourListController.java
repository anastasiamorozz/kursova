package com.example.kursova.controller;

import com.example.kursova.dao.TourDAO;
import com.example.kursova.model.Guide;
import com.example.kursova.model.Tour;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

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

    private final TourDAO tourDAO = new TourDAO();

    @FXML
    private void handleAddTour() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/kursova/form/AddTour.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Додати тур");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL); // блокувати інші вікна
            stage.showAndWait(); // чекати закриття

            refreshTourList(); // ОНОВИТИ ПІСЛЯ ЗАКРИТТЯ
        } catch (IOException e) {
            System.err.println("❌ Помилка відкриття вікна додавання туру: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @FXML
    public void handleAddGuide() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/kursova/guide_list.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Список гідів");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleAddHotel() {
        openWindow("/com/example/kursova/form/AddHotel.fxml", "Додати готель");
    }


    public void refreshTourList() {
        List<Tour> tours = tourDAO.getAllTours();
        tourTable.setItems(FXCollections.observableArrayList(tours));
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

    private void openTourEditWindow(Tour tour) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/kursova/form/AddTour.fxml"));
            Parent root = loader.load();

            AddTourController controller = loader.getController();
            controller.setTourToEdit(tour); // передаємо обʼєкт туру

            Stage stage = new Stage();
            stage.setTitle("Редагування туру");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            refreshTourList(); // після закриття оновити список
        } catch (IOException e) {
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
        languageColumn.setCellValueFactory(cellData -> {
            Guide guide = cellData.getValue().getGuide();
            String lang = (guide != null) ? guide.getLanguage() : "—";
            return new SimpleStringProperty(lang);
        });
        guideColumn.setCellValueFactory(cellData -> {
            Guide guide = cellData.getValue().getGuide();
            String guideText = (guide != null) ? guide.toString() : "—";
            return new SimpleStringProperty(guideText);
        });



//        tourTable.getItems().addAll(new TourDAO().getAllTours());

        tourTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Tour selectedTour = tourTable.getSelectionModel().getSelectedItem();
                if (selectedTour != null) {
                    openTourEditWindow(selectedTour);
                }
            }
        });

        refreshTourList();
    }
}
