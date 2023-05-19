//Package name
package com.example.shiftplaner.controller;
//Importierte Bibliotheken
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//Klasse DatenbankManager, dient zur Verbindungsherstellung,
// zum Laden, Speichern und Löschen der Daten aus der Datenbank.
public class DatenbankManager {
    //Variablen für die Verbindung zur Datenbank
    private static final String URL = "jdbc:mysql://lx8.hoststar.hosting/ch355797_shiftplaner";
    private static final String USER = "ch355797_admin";
    private static final String PASSWORD = "Admin_1234";
    //Verbindung zur Datenbank
    private Connection connection;
    //Methode zur Verbindungsherstellung
    public void connect() throws SQLException {
        try {
            //Verbindungsherstellung
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Verbindung erfolgreich!");
            //Fehler abfangen
        } catch (SQLException e) {
            //Ausgabe der Fehlermeldung
            System.out.println("Verbindung fehlgeschlagen: " + e.getMessage());
            throw e;
        }
    }
    //Methode zum Laden der Daten aus der Datenbank
    public List<String> loadComboBoxItems(String tableName, String columnName) throws SQLException {

        //Liste mit Strings für die ComboBox
        List<String> items = new ArrayList<>();
        //SQL-Abfrage
        String selectSQL = "SELECT " + columnName + " FROM " + tableName;
        //Erstellen des PreparedStatements
        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
        //Ausführen der Abfrage
        ResultSet resultSet = preparedStatement.executeQuery();
        //Durchlaufen der Abfrageergebnisse
        while (resultSet.next()) {
            //Auslesen der Abfrageergebnisse
            String item = resultSet.getString(columnName);
            //Hinzufügen der Abfrageergebnisse zur Liste
            items.add(item);
        }
        //Schließen der Verbindung
        resultSet.close();
        //Schließen des PreparedStatements
        preparedStatement.close();
        //Rückgabe der Abfrageergebnisse als Liste
        return items;
    }

    //Methode zum Speichern der Daten in der Datenbank
    public void saveComboBoxItems(String tableName, String columnName, List<String> items) throws SQLException {
        //SQL-Abfrage
        String insertSQL = "INSERT INTO " + tableName + " (" + columnName + ") VALUES (?)";
        //Erstellen des PreparedStatements
        PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
        //Durchlaufen der Liste
        for (String item : items) {
            //Setzen des Parameters
            preparedStatement.setString(1, item);
            //Ausführen der Abfrage
            preparedStatement.executeUpdate();
        }
        //Schließen der Verbindung
        preparedStatement.close();
    }
    //Methode zum Löschen der Daten aus der Datenbank
    public void deleteMitarbeiter(String name) throws SQLException {
        //SQL-Abfrage
        String deleteSQL = "DELETE FROM Mitarbeiter WHERE mitarbeiterName = ?";
        //Erstellen des PreparedStatements
        PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
        //Setzen des Parameters
        preparedStatement.setString(1, name);
        //Ausführen der Abfrage
        preparedStatement.executeUpdate();
        //Schließen der Verbindung
        preparedStatement.close();
    }

    //Methode zum Trennen der Verbindung
    public void disconnect() {
        //Prüfen, ob eine Verbindung besteht
        if (connection != null) {
            try {
                //Trennen der Verbindung
                connection.close();
                //Ausgabe der Meldung, wenn die Verbindung getrennt wurde
                System.out.println("Verbindung erfolgreich getrennt!");
                //Fehler abfangen
            } catch (SQLException e) {
                //Ausgabe der Fehlermeldung
                System.out.println("Fehler beim Trennen der Verbindung: " + e.getMessage());
            }
        }
    }
}
