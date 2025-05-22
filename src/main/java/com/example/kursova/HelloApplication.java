package com.example.kursova;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/kursova/tour_list.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Список туристичних турів");
        stage.setScene(scene);
        stage.show();

//        System.out.println(getClass().getResource("/com/example/kursova/tour_list.fxml"));

    }

    public static void main(String[] args) {
        launch();
    }
}