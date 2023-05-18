module com.example.shiftplaner {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.shiftplaner to javafx.fxml;
    exports com.example.shiftplaner;
    exports com.example.shiftplaner.controller;
    opens com.example.shiftplaner.controller to javafx.fxml;
}