package com.example.shiftplaner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

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
