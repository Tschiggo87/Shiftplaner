package com.example.shiftplaner.controller;



import com.example.shiftplaner.model.Mitarbeiter;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.BreakIterator;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class MenuButtonController implements Initializable {
    // Definiere einen DateTimeFormatter für das Format "dd.MM.yyyy"
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy");
    private ObservableList<String> mitarbeiterList = FXCollections.observableArrayList();
    private List<ComboBox<String>> comboBoxList;

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
    private ComboBox<String> nameComboBox7;
    @FXML
    private ComboBox<String> nameComboBox8;
    @FXML
    private ComboBox<String> nameComboBox9;
    @FXML
    private ComboBox<String> nameComboBox10;
    @FXML
    private ComboBox<String> nameComboBox11;
    @FXML
    private ComboBox<String> nameComboBox12;
    @FXML
    private ComboBox<String> nameComboBox13;
    @FXML
    private ComboBox<String> nameComboBox14;
    @FXML
    private ComboBox<String> nameComboBox15;
    @FXML
    private TextField employeeNameField;
    @FXML
    public Button mitarbeiterButton;




    // Diese Methode wird aufgerufen, wenn der Controller initialisiert wird
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Fügen Sie alle ComboBox-Elemente zur Liste hinzu
        List<ComboBox<String>> comboBoxes = Arrays.asList(nameComboBox1,nameComboBox2,nameComboBox3,nameComboBox4,nameComboBox5,nameComboBox6,nameComboBox7,nameComboBox8,nameComboBox9,nameComboBox10,nameComboBox11,nameComboBox12,nameComboBox13,nameComboBox14,nameComboBox15);

        for (ComboBox<String> comboBox : comboBoxes) {
            bindComboBoxToMitarbeiterList(comboBox);
        }

        nameComboBox1.itemsProperty().bind(new SimpleListProperty<>(mitarbeiterList));

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
            System.out.println("Mitarbeiter hinzugefügt");
            openAddMitarbeiterWindow();
        });



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

    // Diese Methode wird aufgerufen, wenn der Button "Mitarbeiter hinzufügen" gedrückt wird

    public void openAddMitarbeiterWindow() {
        Stage stage = new Stage();
        stage.setTitle("Mitarbeiter hinzufügen");

        Button saveBtn = new Button("Speichern");
        Label label = new Label("Mitarbeitername");
        TextField textField = new TextField ();
        textField.setPrefWidth(100);

        // Erstellen Sie das Label, das die Bestätigungsnachricht anzeigen wird
        Label confirmationLabel = new Label();

        saveBtn.setOnAction(e -> {
            String name = textField.getText();
            if (!name.isEmpty()) {
                // Sie müssen hier keinen neuen Mitarbeiter erstellen, da Sie nur den Namen speichern
                mitarbeiterList.add(name);
                confirmationLabel.setText("Mitarbeiter " + name + " wurde hinzugefügt");
                textField.clear();
            } else {
                confirmationLabel.setText("Bitte geben Sie einen Namen ein");
            }
        });

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        // Fügen Sie das Bestätigungs-Label zum Layout hinzu
        layout.getChildren().addAll(label, textField, saveBtn, confirmationLabel);

        Scene scene = new Scene(layout, 300, 200);
        stage.setScene(scene);
        stage.show();
    }




    @FXML
    public void addEmployee(ActionEvent event) {

        String employeeName = employeeNameField.getText(); // Mitarbeitername aus dem Textfeld lesen

        // Überprüfen, ob der Mitarbeitername nicht leer ist
        if (!employeeName.isEmpty()) {
            Collection<String> employeeNameList = nameComboBox1.getItems();
            employeeNameList.add(employeeName); // Mitarbeiter zur Liste hinzufügen

            // Mitarbeiterliste in der ComboBox aktualisieren
            nameComboBox1.setItems(FXCollections.observableArrayList(employeeNameList));

            // Optional: Textfeld leeren
            employeeNameField.clear();
        }
    }


}

