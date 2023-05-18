package com.example.shiftplaner;

import com.example.shiftplaner.controller.DatenbankManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        // Erstellen Sie hier eine Instanz der DatenbankManager-Klasse
        DatenbankManager dbManager = new DatenbankManager();
        try {
            dbManager.connect();
            // Führen Sie hier Ihre Datenbankoperationen aus...
        } catch (SQLException e) {
            // Behandeln Sie Verbindungsfehler...
        } finally {
            dbManager.disconnect();
        }

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));

        // Laden der FXML-Datei
        Scene scene = new Scene(fxmlLoader.load(), 1150, 850);

        // Hinzufügen der CSS-Datei zur Szene
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        // Setzen des Titels, der Szene und Anzeigen des Hauptfensters
        stage.setTitle("Schichtplaner!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
