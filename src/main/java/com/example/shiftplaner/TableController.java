package com.example.shiftplaner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


public class TableController implements Initializable {

    @FXML
    private TableView<String[]> tableView;

    @FXML
    private TableColumn<String[], String> column1;
    @FXML
    private TableColumn<String[], String> column2;
    @FXML
    private TableColumn<String[], String> column3;
    @FXML
    private TableColumn<String[], String> column4;
    @FXML
    private TableColumn<String[], String> column5;
    @FXML
    private TableColumn<String[], String> column6;
    @FXML
    private TableColumn<String[], String> column7;
    @FXML
    private TableColumn<String[], String> column8;
    @FXML
    private TableColumn<String[], String> column9;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set column widths
        int numberOfColumns = 9;
        column1.prefWidthProperty().bind(tableView.widthProperty().divide(numberOfColumns));
        column2.prefWidthProperty().bind(tableView.widthProperty().divide(numberOfColumns));
        column3.prefWidthProperty().bind(tableView.widthProperty().divide(numberOfColumns));
        column4.prefWidthProperty().bind(tableView.widthProperty().divide(numberOfColumns));
        column5.prefWidthProperty().bind(tableView.widthProperty().divide(numberOfColumns));
        column6.prefWidthProperty().bind(tableView.widthProperty().divide(numberOfColumns));
        column7.prefWidthProperty().bind(tableView.widthProperty().divide(numberOfColumns));
        column8.prefWidthProperty().bind(tableView.widthProperty().divide(numberOfColumns));
        column9.prefWidthProperty().bind(tableView.widthProperty().divide(numberOfColumns));

        // Add row with date in second column of first row
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String[] row = new String[9];
        row[1] = currentDate.format(formatter);
        ObservableList<String[]> data = FXCollections.observableArrayList();
        data.add(row);

        // Add 20 rows
        for (int i = 0; i < 20; i++) {
            String[] emptyRow = new String[9];
            data.add(emptyRow);
        }

        tableView.setItems(data);
    }
}




