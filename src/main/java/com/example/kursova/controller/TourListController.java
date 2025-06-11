package com.example.kursova.controller;

import com.example.kursova.dao.TourDAO;
import com.example.kursova.enums.TourLanguage;
import com.example.kursova.enums.TourType;
import com.example.kursova.enums.TransportType;
import com.example.kursova.model.Guide;
import com.example.kursova.model.Hotel;
import com.example.kursova.model.Tour;
import com.example.kursova.model.TourFilter;
import com.example.kursova.service.TourService;
import com.example.kursova.utils.LoggerUtil;
import com.example.kursova.utils.SimpleReportGenerator;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class TourListController {

    public ComboBox tourTypeFilter;
    public ComboBox transportTypeFilter;
    public ComboBox languageFilter;
    public TextField minPriceFilter;
    public TextField maxPriceFilter;
    @FXML
    TableView<Tour> tourTable;

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

    private TourService tourService;

    @FXML
    private TextField searchField;

    private List<Tour> tours;

    private static final Logger logger = LoggerUtil.getLogger();

    public TourListController() {
        this.tourService = new TourService();
    }

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

            logger.info("🔧 Користувач відкрив вікно додавання туру");
            refreshTourList(); // ОНОВИТИ ПІСЛЯ ЗАКРИТТЯ
        } catch (IOException e) {
            logger.log(Level.SEVERE, "❌ Помилка відкриття вікна додавання туру", e);
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
        tours = tourService.getAllTours();
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
            logger.log(Level.SEVERE, "❌ Помилка відкриття вікна: " + fxmlPath, e);
            ;
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
    private void handleShowHotelList() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/kursova/hotel_list.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Список готелів");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL); // блокує основне вікно
            stage.showAndWait(); // чекає закриття

        } catch (IOException e) {
            logger.log(Level.SEVERE, "❌ Помилка відкриття списку готелів", e);
        }
    }

    @FXML
    void applyFilters() {
        TourFilter filter = new TourFilter();
        if (tourTypeFilter.getValue() != null)
            filter.setTourType((TourType) tourTypeFilter.getValue());
        if (transportTypeFilter.getValue() != null)
            filter.setTransportType((TransportType) transportTypeFilter.getValue());
        if (languageFilter.getValue() != null)
            filter.setLanguage((TourLanguage) languageFilter.getValue());
        try {
            if (!minPriceFilter.getText().isBlank())
                filter.setMinPrice(Double.parseDouble(minPriceFilter.getText()));
            if (!maxPriceFilter.getText().isBlank())
                filter.setMaxPrice(Double.parseDouble(maxPriceFilter.getText()));
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "❗ Невірно введена ціна", e);
            return;
        }
        List<Tour> filteredTours = tourService.filterTours(filter);
        tourTable.setItems(FXCollections.observableArrayList(filteredTours));
        tours = filteredTours;
        logger.info("🔍 Застосовано фільтри для турів");
    }

    @FXML
    private void initialize() {
        System.out.println("Контролер ініціалізовано успішно!");

        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        hotelColumn.setCellValueFactory(cellData -> {
            Hotel hotel = cellData.getValue().getHotel();
            String hotelName = (hotel != null) ? hotel.getName() : "—";
            return new SimpleStringProperty(hotelName);
        });
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

        tourTypeFilter.getItems().setAll(TourType.values());
        transportTypeFilter.getItems().setAll(TransportType.values());
        languageFilter.getItems().setAll(TourLanguage.values());

        // tourTable.getItems().addAll(new TourDAO().getAllTours());

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

    @FXML
    private void handleSearch() {
        String keyword = searchField.getText();
        List<Tour> filteredTours = tourService.searchTours(keyword);
        tourTable.setItems(FXCollections.observableArrayList(filteredTours));
    }

    @FXML
    private void handleGenerateReport() {
        logger.info("📄 Користувач натиснув кнопку генерації звіту");
        List<Tour> filteredTours = tours; // отримуємо список відфільтрованих турів
        SimpleReportGenerator reportGenerator = new SimpleReportGenerator();
        Stage stage = new Stage();
        reportGenerator.generatePdfReport(filteredTours, stage); // primaryStage - це Stage вашого вікна
    }

}
