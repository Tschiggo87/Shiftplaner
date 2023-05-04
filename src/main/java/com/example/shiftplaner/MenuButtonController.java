package com.example.shiftplaner;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ResourceBundle;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;




public class MenuButtonController implements Initializable {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");


    @FXML
    private Button naechsteWocheButton;

    @FXML
    private Button vorherigeWocheButton;

    @FXML
    private Label wochenNr;

    private LocalDate currentDate;
    @FXML
    private Label montagDate;

    @FXML
    private Label dienstagDate;

    @FXML
    private Label mittwochDate;

    @FXML
    private Label donnerstagDate;

    @FXML
    private Label freitagDate;

    @FXML
    private Label samstagDate;

    @FXML
    private Label sonntagDate;

    @FXML
    private Label wocheVon;

    @FXML
    private Label wocheBis;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currentDate = LocalDate.now();
        updateWeekNumber();

        naechsteWocheButton.setOnAction(event -> {
            currentDate = currentDate.plusWeeks(1);
            updateDates();
            updateWeekNumber();
            weekRange();

        });

        vorherigeWocheButton.setOnAction(event -> {
            currentDate = currentDate.minusWeeks(1);
            updateDates();
            updateWeekNumber();
            weekRange();
        });
    }

    // Formatiert das Datum nach dd.MM.yyyy
    private void updateWeekNumber() {
        int weekNumber = currentDate.get(WeekFields.ISO.weekOfWeekBasedYear());
        wochenNr.setText("Woche: " + weekNumber);

        Label[] dateLabels = {montagDate, dienstagDate, mittwochDate, donnerstagDate, freitagDate, samstagDate, sonntagDate};
        DayOfWeek[] dayOfWeeks = {DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY};

        for (int i = 0; i < dateLabels.length; i++) {
            LocalDate dayDate = currentDate.with(dayOfWeeks[i]);
            String formattedDate = DATE_FORMATTER.format(dayDate);
            dateLabels[i].setText(formattedDate);
        }
    }

    private void weekRange() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate weekBegin = currentDate.with(DayOfWeek.MONDAY);
        wocheVon.setText(dateFormatter.format(weekBegin));

        LocalDate weekEnd = currentDate.with(DayOfWeek.SUNDAY);
        wocheBis.setText(dateFormatter.format(weekEnd));
    }

    private void updateDates() {
        LocalDate monday = currentDate.with(DayOfWeek.MONDAY);
        montagDate.setText(monday.toString());
        dienstagDate.setText(monday.plusDays(1).toString());
        mittwochDate.setText(monday.plusDays(2).toString());
        donnerstagDate.setText(monday.plusDays(3).toString());
        freitagDate.setText(monday.plusDays(4).toString());
        samstagDate.setText(monday.plusDays(5).toString());
        sonntagDate.setText(monday.plusDays(6).toString());
    }



}
