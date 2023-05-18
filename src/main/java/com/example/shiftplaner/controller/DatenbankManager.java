package com.example.shiftplaner.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatenbankManager {
    private static final String URL = "jdbc:mysql://bftnhjbkmopf6e2hqn5f-mysql.services.clever-cloud.com:3306/bftnhjbkmopf6e2hqn5f";
    private static final String USER = "ud4zbj8g0ki42o4q";
    private static final String PASSWORD = "KaeF9a9y8AOgNgbBp6K8";

    private Connection connection;

    public void connect() throws SQLException {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Verbindung erfolgreich!");
        } catch (SQLException e) {
            System.out.println("Verbindung fehlgeschlagen: " + e.getMessage());
            throw e;
        }
    }

    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Verbindung erfolgreich getrennt!");
            } catch (SQLException e) {
                System.out.println("Fehler beim Trennen der Verbindung: " + e.getMessage());
            }
        }
    }
}

