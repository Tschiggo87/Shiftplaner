package com.example.shiftplaner.controller;


import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;

import java.security.cert.PolicyNode;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DatenbankManager {
    private static final String URL = "jdbc:mysql://lx8.hoststar.hosting/ch355797_shiftplaner";
    private static final String USER = "ch355797_admin";
    private static final String PASSWORD = "Admin_1234";

    private Pane gridPane;

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

    public List<String> loadComboBoxItems(String tableName, String columnName) throws SQLException {
        List<String> items = new ArrayList<>();
        String selectSQL = "SELECT " + columnName + " FROM " + tableName;
        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String item = resultSet.getString(columnName);
            items.add(item);
        }
        resultSet.close();
        preparedStatement.close();
        return items;
    }

    public void saveComboBoxItems(String tableName, String columnName, List<String> items) throws SQLException {
        String deleteSQL = "DELETE FROM " + tableName;
        PreparedStatement deleteStatement = connection.prepareStatement(deleteSQL);
        deleteStatement.executeUpdate();
        deleteStatement.close();

        String insertSQL = "INSERT INTO " + tableName + " (" + columnName + ") VALUES (?)";
        PreparedStatement insertStatement = connection.prepareStatement(insertSQL);
        for (String item : items) {
            insertStatement.setString(1, item);
            insertStatement.executeUpdate();
        }
        insertStatement.close();
    }
    public void deleteMitarbeiter(String name) throws SQLException {
        String deleteSQL = "DELETE FROM Mitarbeiter WHERE mitarbeiterName = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
        preparedStatement.setString(1, name);
        preparedStatement.executeUpdate();
        preparedStatement.close();
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
