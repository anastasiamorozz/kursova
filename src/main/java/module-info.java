module com.example.kursova {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires kernel;
    requires java.desktop;
    requires layout;
    requires io;

    opens com.example.kursova to javafx.fxml;
    opens com.example.kursova.controller to javafx.fxml;
    opens com.example.kursova.model to javafx.base;

    exports com.example.kursova;
    exports com.example.kursova.controller;
}