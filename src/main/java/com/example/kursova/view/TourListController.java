package com.example.kursova.view;

import com.example.kursova.model.Tour;
import com.example.kursova.dao.TourDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TourListController {

    @FXML
    private TableView<Tour> tourTable;

    @FXML
    private TableColumn<Tour, String> titleColumn;

    @FXML
    private TableColumn<Tour, String> hotelColumn;

    private final TourDAO tourDAO = new TourDAO();

    @FXML
    private void initialize() {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        tourTable.setItems(FXCollections.observableArrayList(tourDAO.getAllTours()));

        hotelColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getHotel().toString()));

        System.out.println("Контролер ініціалізовано успішно!");
    }
}
