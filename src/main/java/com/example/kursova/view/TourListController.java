package com.example.kursova.view;

import com.example.kursova.dao.TourDAO;
import com.example.kursova.model.Tour;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TourListController {
    @FXML private TableView<?> tourTable;
    @FXML private TableColumn<?, ?> titleColumn;


//    @FXML
//    private TableView<Tour> tourTable;
//    @FXML
//    private TableColumn<Tour, String> titleColumn;
//    @FXML
//    private TableColumn<Tour, String> typeColumn;
//    @FXML
//    private TableColumn<Tour, Integer> daysColumn;
//    @FXML
//    private TableColumn<Tour, Double> priceColumn;
//
//    private final TourDAO tourDAO = new TourDAO();
//    private final ObservableList<Tour> tourData = FXCollections.observableArrayList();
//
//    @FXML
//    private void initialize() {
//        titleColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getTitle()));
//        typeColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getTourType().name()));
//        daysColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getDays()).asObject());
//        priceColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
//
//        tourData.addAll(tourDAO.getAllTours());
//        tourTable.setItems(tourData);
//    }
}
