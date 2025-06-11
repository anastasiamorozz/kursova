package com.example.kursova.controller;

import com.example.kursova.dao.HotelDAO;
import com.example.kursova.model.Hotel;
import com.example.kursova.service.HotelService;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class HotelListController {

    @FXML
    TableView<Hotel> hotelTable;
    @FXML
    private TableColumn<Hotel, String> nameColumn;
    @FXML
    private TableColumn<Hotel, Integer> starsColumn;
    @FXML
    private TableColumn<Hotel, String> countryColumn;
    @FXML
    private TableColumn<Hotel, String> cityColumn;
    @FXML
    Label errorLabel;

    private final HotelDAO hotelDAO = new HotelDAO();
    private HotelService hotelService;

    public HotelListController() {
        this.hotelService = new HotelService();
    }

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
        starsColumn.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getStars()).asObject());
        countryColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCountry()));
        cityColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCity()));

        refreshHotelList();
    }

    @FXML
    void handleAddHotel() {
        openHotelForm(null);
    }

    @FXML
    void handleEditHotel() {
        Hotel selected = hotelTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            openHotelForm(selected);
        } else {
            errorLabel.setText("Оберіть готель для редагування.");
        }
    }

    @FXML
    void handleDeleteHotel() {
        Hotel selected = hotelTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            hotelService.deleteHotel(selected.getName());
            refreshHotelList();
        } else {
            errorLabel.setText("Оберіть готель для видалення.");
        }
    }

    private void openHotelForm(Hotel hotelToEdit) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/kursova/form/AddHotel.fxml"));
            Parent root = loader.load();

            AddHotelController controller = loader.getController();
            if (hotelToEdit != null)
                controller.setHotelToEdit(hotelToEdit);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(hotelToEdit == null ? "Додати готель" : "Редагувати готель");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            refreshHotelList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void refreshHotelList() {
        List<Hotel> hotels = hotelService.getAllHotels();
        hotelTable.setItems(FXCollections.observableArrayList(hotels));
    }
}
