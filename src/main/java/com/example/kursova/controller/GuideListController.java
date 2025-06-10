package com.example.kursova.controller;

import com.example.kursova.dao.GuideDAO;
import com.example.kursova.model.Guide;
import com.example.kursova.enums.TourLanguage;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class GuideListController {

    public TextField searchField;
    @FXML private TableView<Guide> guideTable;
    @FXML private TableColumn<Guide, String> nameColumn;
    @FXML private TableColumn<Guide, String> languageColumn;
    @FXML private TableColumn<Guide, String> phoneColumn;
    @FXML private Label errorLabel;

    private final GuideDAO guideDAO = new GuideDAO();

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        languageColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLanguage()));
        phoneColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPhone()));

        refreshGuideList();
    }

    public void refreshGuideList() {
        List<Guide> guides = guideDAO.getAllGuides();
        guideTable.setItems(FXCollections.observableArrayList(guides));
    }

    @FXML
    public void handleAddGuide() {
        openGuideForm(null);
    }

    @FXML
    public void handleEditGuide() {
        Guide selected = guideTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            openGuideForm(selected);
        } else {
            errorLabel.setText("Оберіть гіда для редагування");
        }
    }

    @FXML
    public void handleDeleteGuide() {
        Guide selected = guideTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            guideDAO.deleteGuide(selected.getId());
            refreshGuideList();
        } else {
            errorLabel.setText("Оберіть гіда для видалення");
        }
    }

    private void openGuideForm(Guide guideToEdit) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/kursova/form/AddGuide.fxml"));
            Parent root = loader.load();

            AddGuideController controller = loader.getController();
            if (guideToEdit != null) {
                controller.setGuideToEdit(guideToEdit);
            }

            Stage stage = new Stage();
            stage.setTitle(guideToEdit == null ? "Додати гіда" : "Редагувати гіда");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            refreshGuideList();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSearch() {
        String keyword = searchField.getText().toLowerCase();

        List<Guide> allGuids = guideDAO.getAllGuides(); // або збережений список, якщо вже є
        List<Guide> filteredGuids = allGuids.stream()
                .filter(el -> el.getName().toLowerCase().contains(keyword))
                .collect(Collectors.toList());

        guideTable.setItems(FXCollections.observableArrayList(filteredGuids));
    }
}
