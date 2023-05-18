package com.example.shiftplaner.controller;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;


public class MenuButtonController implements Initializable {
    // Definiere einen DateTimeFormatter für das Format "dd.MM.yyyy"
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy");
    private ObservableList<String> mitarbeiterList = FXCollections.observableArrayList();
    private ObservableList<String> schichtList = FXCollections.observableArrayList();
    private boolean isMitarbeiterWindowOpen = false; // Instanzvariable, um den Zustand des Fensters zu speichern
    private Stage mitarbeiterWindow; // Instanzvariable, um das Mitarbeiterfenster zu speichern
    private boolean isSchichtWindowOpen = false; // Instanzvariable, um den Zustand des Fensters zu speichern
    private Stage schichtWindow; // Instanzvariable, um das Schichtfenster zu speichern


    // FXML-Elemente, auf die der Controller zugreift
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
    @FXML
    private ComboBox<String> nameComboBox1;
    @FXML
    private ComboBox<String> nameComboBox2;
    @FXML
    private ComboBox<String> nameComboBox3;
    @FXML
    private ComboBox<String> nameComboBox4;
    @FXML
    private ComboBox<String> nameComboBox5;
    @FXML
    private ComboBox<String> nameComboBox6;
    @FXML
    private ComboBox<String> schichtComboBox1;
    @FXML
    private ComboBox<String> schichtComboBox2;
    @FXML
    private ComboBox<String> schichtComboBox3;
    @FXML
    private ComboBox<String> schichtComboBox4;
    @FXML
    private ComboBox<String> schichtComboBox5;
    @FXML
    private ComboBox<String> schichtComboBox6;
    @FXML
    private ComboBox<String> schichtComboBox7;
    @FXML
    private ComboBox<String> schichtComboBox8;
    @FXML
    private ComboBox<String> schichtComboBox9;
    @FXML
    private ComboBox<String> schichtComboBox10;
    @FXML
    private ComboBox<String> schichtComboBox11;
    @FXML
    private ComboBox<String> schichtComboBox12;
    @FXML
    private ComboBox<String> schichtComboBox13;
    @FXML
    private ComboBox<String> schichtComboBox14;
    @FXML
    private ComboBox<String> schichtComboBox15;
    @FXML
    private ComboBox<String> schichtComboBox16;
    @FXML
    private ComboBox<String> schichtComboBox17;
    @FXML
    private ComboBox<String> schichtComboBox18;
    @FXML
    private ComboBox<String> schichtComboBox19;
    @FXML
    private ComboBox<String> schichtComboBox20;
    @FXML
    private ComboBox<String> schichtComboBox21;
    @FXML
    private ComboBox<String> schichtComboBox22;
    @FXML
    private ComboBox<String> schichtComboBox23;
    @FXML
    private ComboBox<String> schichtComboBox24;
    @FXML
    private ComboBox<String> schichtComboBox25;
    @FXML
    private ComboBox<String> schichtComboBox26;
    @FXML
    private ComboBox<String> schichtComboBox27;
    @FXML
    private ComboBox<String> schichtComboBox28;
    @FXML
    private ComboBox<String> schichtComboBox29;
    @FXML
    private ComboBox<String> schichtComboBox30;
    @FXML
    private ComboBox<String> schichtComboBox31;
    @FXML
    private ComboBox<String> schichtComboBox32;
    @FXML
    private ComboBox<String> schichtComboBox33;
    @FXML
    private ComboBox<String> schichtComboBox34;
    @FXML
    private ComboBox<String> schichtComboBox35;
    @FXML
    private ComboBox<String> schichtComboBox36;
    @FXML
    private ComboBox<String> schichtComboBox37;
    @FXML
    private ComboBox<String> schichtComboBox38;
    @FXML
    private ComboBox<String> schichtComboBox39;
    @FXML
    private ComboBox<String> schichtComboBox40;
    @FXML
    private ComboBox<String> schichtComboBox41;
    @FXML
    private ComboBox<String> schichtComboBox42;
    @FXML
    public Button mitarbeiterButton;
    @FXML
    public Button schichtButton;
    @FXML
    public Button saveButton;
    @FXML
    private GridPane gridPane;


    // Diese Methode wird aufgerufen, wenn der Controller initialisiert wird
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Fügen Sie alle namenComboBox-Elemente zur Liste hinzu
        List<ComboBox<String>> comboBoxes = Arrays.asList(nameComboBox1,nameComboBox2,nameComboBox3,nameComboBox4,nameComboBox5,nameComboBox6);
        for (ComboBox<String> comboBox : comboBoxes) {
            bindComboBoxToMitarbeiterList(comboBox);
        }

        // Füge alle schichtComboBoxen zur Liste hinzu
        List<ComboBox<String>> schichtComboBoxe = Arrays.asList(schichtComboBox1,schichtComboBox2,schichtComboBox3,schichtComboBox4,schichtComboBox5,schichtComboBox6,schichtComboBox7,schichtComboBox8,schichtComboBox9,schichtComboBox10,schichtComboBox11,schichtComboBox12,schichtComboBox13,schichtComboBox14,schichtComboBox15,schichtComboBox16,schichtComboBox17,schichtComboBox18,schichtComboBox19,schichtComboBox20,schichtComboBox21,schichtComboBox22,schichtComboBox23,schichtComboBox24,schichtComboBox25,schichtComboBox26,schichtComboBox27,schichtComboBox28,schichtComboBox29,schichtComboBox30,schichtComboBox31,schichtComboBox32,schichtComboBox33,schichtComboBox34,schichtComboBox35,schichtComboBox36,schichtComboBox37,schichtComboBox38,schichtComboBox39,schichtComboBox40,schichtComboBox41,schichtComboBox42);
        for (ComboBox<String> comboBox : schichtComboBoxe) {
            bindComboBoxToSchichtList(comboBox);
        }

        currentDate = LocalDate.now(); // Setze das aktuelle Datum
        updateWeekNumber(); // Aktualisiere die Woche und die Datumsangaben in der UI
        weekRange();

        // Definiere die Aktionen für die Buttons, um zur nächsten bzw. vorherigen Woche zu navigieren
        naechsteWocheButton.setOnAction(event -> {
            currentDate = currentDate.plusWeeks(1); // Addiere eine Woche zum aktuellen Datum
            updateDates(); // Aktualisiere die Datumsangaben in der UI
            updateWeekNumber(); // Aktualisiere die Woche in der UI
            weekRange(); // Aktualisiere die Angabe für den Wochenbeginn und das Wochenende in der UI
        });

        vorherigeWocheButton.setOnAction(event -> {
            currentDate = currentDate.minusWeeks(1); // Subtrahiere eine Woche vom aktuellen Datum
            updateDates(); // Aktualisiere die Datumsangaben in der UI
            updateWeekNumber(); // Aktualisiere die Woche in der UI
            weekRange(); // Aktualisiere die Angabe für den Wochenbeginn und das Wochenende in der UI
        });

        mitarbeiterButton.setOnAction(event -> {
            openAddMitarbeiterWindow();
        });

        schichtButton.setOnAction(event -> {
            openAddSchichtWindow();
        });

        saveButton.setOnAction(event -> {

        });
    }

    private void bindComboBoxToSchichtList(ComboBox<String> comboBox) {
        comboBox.itemsProperty().bind(new SimpleListProperty<>(schichtList));
    }

    // Diese Methode aktualisiert die Woche und die Datumsangaben in der UI
    private void updateWeekNumber() {
        int weekNumber = currentDate.get(WeekFields.ISO.weekOfWeekBasedYear()); // Ermittle die aktuelle Woche
        wochenNr.setText("Woche: " + weekNumber); // Setze die Woche in der UI

        // Erstelle Arrays für die Labels der einzelnen Wochentage und die entsprechenden DayOfWeek-Konstanten
        Label[] dateLabels = {montagDate, dienstagDate, mittwochDate, donnerstagDate, freitagDate, samstagDate, sonntagDate};
        DayOfWeek[] dayOfWeeks = {DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY};
        // Iteriere über die Labels der einzelnen Wochentage und setze das entsprechende Datum in der UI
        for (int i = 0; i < dateLabels.length; i++) {
            LocalDate dayDate = currentDate.with(dayOfWeeks[i]); // Ermittle das Datum des entsprechenden Wochentags
            String formattedDate = DATE_FORMATTER.format(dayDate); // Formatiere das Datum
            dateLabels[i].setText(formattedDate); // Setze das Datum in der UI
        }
    }

    // Diese Methode aktualisiert die Angabe für den Wochenbeginn und das Wochenende in der UI
    private void weekRange() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy"); // Definiere einen DateTimeFormatter für das Format "dd.MM.yyyy"
        LocalDate weekBegin = currentDate.with(DayOfWeek.MONDAY); // Ermittle das Datum des Wochenbeginns
        wocheVon.setText(dateFormatter.format(weekBegin)); // Setze das Datum des Wochenbeginns in der UI

        LocalDate weekEnd = currentDate.with(DayOfWeek.SUNDAY); // Ermittle das Datum des Wochenendes
        wocheBis.setText(dateFormatter.format(weekEnd)); // Setze das Datum des Wochenendes in der UI
    }

    // Diese Methode aktualisiert die Datumsangaben in der UI
    private void updateDates() {
        LocalDate monday = currentDate.with(DayOfWeek.MONDAY); // Ermittle das Datum des Montags
        montagDate.setText(monday.toString()); // Setze das Datum des Montags in der UI
        dienstagDate.setText(monday.plusDays(1).toString()); // Setze das Datum des Dienstags in der UI
        mittwochDate.setText(monday.plusDays(2).toString()); // Setze das Datum des Mittwochs in der UI
        donnerstagDate.setText(monday.plusDays(3).toString()); // Setze das Datum des Donnerstags in der UI
        freitagDate.setText(monday.plusDays(4).toString()); // Setze das Datum des Freitags in der UI
        samstagDate.setText(monday.plusDays(5).toString()); // Setze das Datum des Samstags in der UI
        sonntagDate.setText(monday.plusDays(6).toString()); // Setze das Datum des Sonntags in der UI
    }

    private void bindComboBoxToMitarbeiterList(ComboBox<String> comboBox) {
        comboBox.itemsProperty().bind(new SimpleListProperty<>(mitarbeiterList));
    }

    public void openAddMitarbeiterWindow() {
        if (isMitarbeiterWindowOpen) {
            mitarbeiterWindow.toFront();
            return; // Wenn das Fenster bereits geöffnet ist, beenden Sie die Methode
        }

        Stage stage = new Stage();
        stage.setTitle("Mitarbeiter hinzufügen");

        Button saveBtn = new Button("Speichern");
        Label label = new Label("Mitarbeitername");
        TextField textField = new TextField();
        textField.setMaxWidth(100);

        // Erstellen Sie das Label, das die Bestätigungsnachricht anzeigen wird
        Label confirmationLabel = new Label();

        // ComboBox mit den hinzugefügten Mitarbeitern
        Label mitarbeiterLabel = new Label("Bereits hinzugefügte Mitarbeiter");
        ComboBox<String> comboBox = new ComboBox<>(mitarbeiterList);

        // Button zum Löschen des ausgewählten Namens aus der ComboBox
        Button deleteBtn = new Button("Löschen");
        deleteBtn.setOnAction(event -> {
            String selectedName = comboBox.getSelectionModel().getSelectedItem();
            if (selectedName != null) {
                mitarbeiterList.remove(selectedName);
                confirmationLabel.setText("Mitarbeiter " + selectedName + " wurde gelöscht");
            }
        });

        saveBtn.setOnAction(e -> {
            String name = textField.getText();
            if (!name.isEmpty()) {
                mitarbeiterList.add(name);
                confirmationLabel.setText("Mitarbeiter " + name + " wurde hinzugefügt");
                textField.clear();
            } else {
                confirmationLabel.setText("Bitte geben Sie einen Namen ein");
            }
        });

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        // Fügen Sie das Bestätigungs-Label, Textfeld und den Speichern-Button zum Layout hinzu
        layout.getChildren().addAll(label, textField, saveBtn, confirmationLabel, mitarbeiterLabel, comboBox, deleteBtn);

        Scene scene = new Scene(layout, 300, 300);
        stage.setScene(scene);
        stage.show();
        mitarbeiterWindow = stage;
        isMitarbeiterWindowOpen = true;

        stage.setOnCloseRequest(event -> {
            isMitarbeiterWindowOpen = false; // Setzen Sie den Status auf 'false', wenn das Fenster geschlossen wird
        });
    }


    public void openAddSchichtWindow() {
        if (isSchichtWindowOpen) {
            schichtWindow.toFront();
            return; // Wenn das Fenster bereits geöffnet ist, beenden Sie die Methode
        }

        Stage stage = new Stage();
        stage.setTitle("Schicht hinzufügen");

        Button saveBtn = new Button("Speichern");
        Label label = new Label("Schichtname");
        TextField textField = new TextField();
        textField.setMaxWidth(100);

        // Erstellen Sie das Label, das die Bestätigungsnachricht anzeigen wird
        Label confirmationLabel = new Label();

        // ComboBox mit den hinzugefügten Schichten
        Label schichtLabel = new Label("Bereits hinzugefügte Schichten");
        ComboBox<String> comboBox = new ComboBox<>(schichtList);

        // Button zum Löschen des ausgewählten Namens aus der ComboBox
        Button deleteBtn = new Button("Löschen");
        deleteBtn.setOnAction(event -> {
            String selectedName = comboBox.getSelectionModel().getSelectedItem();
            if (selectedName != null) {
                schichtList.remove(selectedName);
                confirmationLabel.setText("Schicht " + selectedName + " wurde gelöscht");
            }
        });

        saveBtn.setOnAction(e -> {
            String name = textField.getText();
            if (!name.isEmpty()) {
                schichtList.add(name);
                confirmationLabel.setText("Schicht " + name + " wurde hinzugefügt");
                textField.clear();
            } else {
                confirmationLabel.setText("Bitte geben Sie einen Namen der Schicht ein");
            }
        });

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        // Fügen Sie das Bestätigungs-Label, Textfeld und den Speichern-Button zum Layout hinzu
        layout.getChildren().addAll(label, textField, saveBtn, confirmationLabel, schichtLabel, comboBox, deleteBtn);

        Scene scene = new Scene(layout, 300, 300);
        stage.setScene(scene);
        stage.show();
        schichtWindow = stage;
        isSchichtWindowOpen = true;

        stage.setOnCloseRequest(event -> {
            isSchichtWindowOpen = false; // Setzen Sie den Status auf 'false', wenn das Fenster geschlossen wird
        });
    }
}

