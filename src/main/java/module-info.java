module com.example.kursova {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.kursova to javafx.fxml;
    opens com.example.kursova.view to javafx.fxml;
    opens com.example.kursova.model to javafx.base;

    exports com.example.kursova;
    exports com.example.kursova.view;
}