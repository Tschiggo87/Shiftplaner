//Package name
package com.example.shiftplaner.controller;
//Importierte Klassen
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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;

//Klasse MenuButtonController, dient zur Steuerung der Menü-Buttons
public class MenuButtonController implements Initializable {
    // Definiere einen DateTimeFormatter für das Format "dd.MM.yyyy"
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy");
    // Definite Mitarbeiter-Liste
    private ObservableList<String> mitarbeiterList = FXCollections.observableArrayList();
    //Definiert die Schicht-Liste
    private ObservableList<String> schichtList = FXCollections.observableArrayList();
    // Instanzvariable, um den Zustand des Fensters zu speichern
    private boolean isMitarbeiterWindowOpen = false;
    // Instanzvariable, um das Mitarbeiterfenster zu speichern
    private Stage mitarbeiterWindow;
    // Instanzvariable, um den Zustand des Fensters zu speichern
    private boolean isSchichtWindowOpen = false;
    // Instanzvariable, um das Schichtfenster zu speichern
    private Stage schichtWindow;




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
        // Verbindung zur Datenbank herstellen methode wird aufgerufen
        boolean isDataLoaded = false; // Flag-Variable, um zu überprüfen, ob die Daten bereits geladen wurden

        try {
            DatenbankManager dbManager = new DatenbankManager();
            dbManager.connect();

            if (!isDataLoaded) {
                // Daten aus der Datenbank laden, wenn sie noch nicht geladen wurden
                loadComboBoxItemsFromDatabase(dbManager);
                isDataLoaded = true; // Flag-Variable aktualisieren
            }

            dbManager.disconnect();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }




        // Fügen Sie alle namenComboBox-Elemente zur Liste hinzu
        List<ComboBox<String>> comboBoxes = Arrays.asList(
                nameComboBox1, nameComboBox2, nameComboBox3,
                nameComboBox4, nameComboBox5, nameComboBox6);
        // Füge alle namenComboBoxen zur Liste hinzu
        for (ComboBox<String> comboBox : comboBoxes) {
            // Füge alle Mitarbeiter zur ComboBox hinzu
            bindComboBoxToMitarbeiterList(comboBox);
        }

        // Füge alle schichtComboBoxen zur Liste hinzu
        List<ComboBox<String>> schichtComboBoxe = Arrays.asList(
                schichtComboBox1, schichtComboBox2, schichtComboBox3,
                schichtComboBox4, schichtComboBox5, schichtComboBox6,
                schichtComboBox7, schichtComboBox8, schichtComboBox9,
                schichtComboBox10, schichtComboBox11, schichtComboBox12,
                schichtComboBox13, schichtComboBox14, schichtComboBox15,
                schichtComboBox16, schichtComboBox17, schichtComboBox18,
                schichtComboBox19, schichtComboBox20, schichtComboBox21,
                schichtComboBox22, schichtComboBox23, schichtComboBox24,
                schichtComboBox25, schichtComboBox26, schichtComboBox27,
                schichtComboBox28, schichtComboBox29, schichtComboBox30,
                schichtComboBox31, schichtComboBox32, schichtComboBox33,
                schichtComboBox34, schichtComboBox35, schichtComboBox36,
                schichtComboBox37, schichtComboBox38, schichtComboBox39,
                schichtComboBox40, schichtComboBox41, schichtComboBox42);
        // Füge alle schichtComboBoxen zur Liste hinzu
        for (ComboBox<String> comboBox : schichtComboBoxe) {
            // Füge alle Schichten zur ComboBox hinzu
            bindComboBoxToSchichtList(comboBox);
        }
        // Setze das aktuelle Datum
        currentDate = LocalDate.now();
        // Aktualisiere die Woche und die Datumsangaben in der UI
        updateWeekNumber();
        // Aktualisiere die Woche und die Datumsangaben in der UI
        weekRange();

        // Definiere die Aktionen für die Buttons, um zur nächsten bzw. vorherigen Woche zu navigieren
        naechsteWocheButton.setOnAction(event -> {
            // Addiere eine Woche zum aktuellen Datum
            currentDate = currentDate.plusWeeks(1);
            // Aktualisiere die Datumsangaben in der UI
            updateDates();
            // Aktualisiere die Woche in der UI
            updateWeekNumber();
            // Aktualisiere die Angabe für den Wochenbeginn und das Wochenende in der UI
            weekRange();
        });

        // Definiere die Aktionen für die Buttons, um zur nächsten bzw. vorherigen Woche zu navigieren
        vorherigeWocheButton.setOnAction(event -> {
            // Subtrahiere eine Woche vom aktuellen Datum
            currentDate = currentDate.minusWeeks(1);
            // Aktualisiere die Datumsangaben in der UI
            updateDates();
            // Aktualisiere die Woche in der UI
            updateWeekNumber();
            // Aktualisiere die Angabe für den Wochenbeginn und das Wochenende in der UI
            weekRange();
        });

        // Definiere die Aktionen für die Buttons, um zur nächsten bzw. vorherigen Woche zu navigieren
        mitarbeiterButton.setOnAction(event -> {
            // Öffne das Fenster zum Hinzufügen eines Mitarbeiters
            openAddMitarbeiterWindow();
        });

        // Definiere die Aktionen für die Buttons, um zur nächsten bzw. vorherigen Woche zu navigieren
        schichtButton.setOnAction(event -> {
            // Öffne das Fenster zum Hinzufügen einer Schicht
            openAddSchichtWindow();
        });

        // Definiere die Aktionen für die Buttons, um zur nächsten bzw. vorherigen Woche zu navigieren
        saveButton.setOnAction(event -> {
            // Speichere die Daten in der Datenbank

        });
    }

    // Diese Methode öffnet das Fenster zum Hinzufügen eines Mitarbeiters
    private void bindComboBoxToSchichtList(ComboBox<String> comboBox) {
        // Erstelle eine Liste mit allen Schichten
        comboBox.itemsProperty().bind(new SimpleListProperty<>(schichtList));
    }

    // Diese Methode aktualisiert die Woche und die Datumsangaben in der UI
    private void updateWeekNumber() {
        // Ermittle die aktuelle Woche
        int weekNumber = currentDate.get(WeekFields.ISO.weekOfWeekBasedYear());
        // Setze die Woche in der UI
        wochenNr.setText("Woche: " + weekNumber);

        // Erstelle Arrays für die Labels der einzelnen Wochentage und die entsprechenden DayOfWeek-Konstanten
        Label[] dateLabels = {
                //WochenTage in der UI
                montagDate, dienstagDate, mittwochDate,
                donnerstagDate, freitagDate, samstagDate, sonntagDate};
        // Erstelle ein Array mit den DayOfWeek-Konstanten
        DayOfWeek[] dayOfWeeks = {DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
                DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY};
        // Iteriere über die Labels der einzelnen Wochentage und setze das entsprechende Datum in der UI
        for (int i = 0; i < dateLabels.length; i++) {
            // Ermittle das Datum des entsprechenden Wochentags
            LocalDate dayDate = currentDate.with(dayOfWeeks[i]);
            // Formatiere das Datum
            String formattedDate = DATE_FORMATTER.format(dayDate);
            // Setze das Datum in der UI
            dateLabels[i].setText(formattedDate);
        }
    }

    // Diese Methode aktualisiert die Angabe für den Wochenbeginn und das Wochenende in der UI
    private void weekRange() {
        // Definiere einen DateTimeFormatter für das Format "dd.MM.yyyy"
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        // Ermittle das Datum des Wochenbeginns
        LocalDate weekBegin = currentDate.with(DayOfWeek.MONDAY);
        // Setze das Datum des Wochenbeginns in der UI
        wocheVon.setText(dateFormatter.format(weekBegin));

        // Ermittle das Datum des Wochenendes
        LocalDate weekEnd = currentDate.with(DayOfWeek.SUNDAY);
        // Setze das Datum des Wochenendes in der UI
        wocheBis.setText(dateFormatter.format(weekEnd));
    }

    // Diese Methode aktualisiert die Datumsangaben in der UI
    private void updateDates() {
        // Ermittle das Datum des Montags
        LocalDate monday = currentDate.with(DayOfWeek.MONDAY);
        // Setze das Datum des Montags in der UI
        montagDate.setText(monday.toString());
        // Setze das Datum des Dienstags in der UI
        dienstagDate.setText(monday.plusDays(1).toString());
        // Setze das Datum des Mittwochs in der UI
        mittwochDate.setText(monday.plusDays(2).toString());
        // Setze das Datum des Donnerstags in der UI
        donnerstagDate.setText(monday.plusDays(3).toString());
        // Setze das Datum des Freitags in der UI
        freitagDate.setText(monday.plusDays(4).toString());
        // Setze das Datum des Samstags in der UI
        samstagDate.setText(monday.plusDays(5).toString());
        // Setze das Datum des Sonntags in der UI
        sonntagDate.setText(monday.plusDays(6).toString());
    }

    // Diese Methode öffnet das Fenster zum Hinzufügen eines Mitarbeiters
    private void bindComboBoxToMitarbeiterList(ComboBox<String> comboBox) {
        // Erstelle eine Liste mit allen Mitarbeitern
        comboBox.itemsProperty().bind(new SimpleListProperty<>(mitarbeiterList));
    }

    // Diese Methode öffnet das Fenster zum Hinzufügen eines Mitarbeiters
    public void openAddMitarbeiterWindow() {
        // Überprüfen Sie, ob das Fenster bereits geöffnet ist
        if (isMitarbeiterWindowOpen) {
            // Wenn das Fenster bereits geöffnet ist, bringen Sie es nach vorne
            mitarbeiterWindow.toFront();
            // Beenden Sie die Methode
            return;
        }

        // Erstellt ein neues Fenster
        Stage stage = new Stage();
        // Setze die Eigenschaften des Fensters
        stage.setTitle("Mitarbeiter hinzufügen");

        // Erstelle ein GridPane
        Button saveBtn = new Button("Speichern");
        // Definiere die Aktionen für den Speichern-Button
        Label label = new Label("Mitarbeitername");
        // Erstellen Sie das Textfeld, in dem der Name des Mitarbeiters eingegeben wird
        TextField textField = new TextField();
        // Begrenzen Sie die maximale Breite des Textfelds
        textField.setMaxWidth(100);

        // Erstellen Sie das Label, das die Bestätigungsnachricht anzeigen wird
        Label confirmationLabel = new Label();

        // Erdstellt ein GridPane
        Label mitarbeiterLabel = new Label("Bereits hinzugefügte Mitarbeiter");
        // ComboBox mit den hinzugefügten Mitarbeitern
        ComboBox<String> comboBox = new ComboBox<>(mitarbeiterList);

        // Button zum Löschen des ausgewählten Namens aus der ComboBox
        Button deleteBtn = new Button("Löschen");
        // Definiere die Aktionen für den Löschen-Button
        deleteBtn.setOnAction(event -> {
            //Hier wird der ausgewählte Name aus der ComboBox gelöscht
            String selectedName = comboBox.getSelectionModel().getSelectedItem();
            // Überprüfen Sie, ob ein Name ausgewählt wurde
            if (selectedName != null) {
                // Löschen Sie den ausgewählten Namen aus der Liste
                mitarbeiterList.remove(selectedName);
                // Setzen Sie die Bestätigungsnachricht
                confirmationLabel.setText("Mitarbeiter " + selectedName + " wurde gelöscht");

                // Löschen Sie den ausgewählten Namen aus der Datenbank
                try {
                    // Verbindung zur Datenbank herstellen
                    DatenbankManager dbManager = new DatenbankManager();
                    // Verbindung zur Datenbank herstellen
                    dbManager.connect();

                    // Mitarbeiter aus der Datenbank löschen
                    dbManager.deleteMitarbeiter(selectedName);

                    // Verbindung zur Datenbank trennen
                    dbManager.disconnect();
                    // Fehlerbehandlung
                } catch (SQLException ex) {
                    // Fehlerbehandlung
                    ex.printStackTrace();
                }
            }
        });

        // Button zum Hinzufügen eines Mitarbeiters
        saveBtn.setOnAction(e -> {
            String name = textField.getText();
            if (!name.isEmpty()) {
                // Überprüfen, ob der Name bereits in der Mitarbeiterliste vorhanden ist
                if (mitarbeiterList.contains(name)) {
                    confirmationLabel.setText("Mitarbeiter " + name + " ist bereits hinzugefügt");
                } else {
                    mitarbeiterList.add(name);
                    confirmationLabel.setText("Mitarbeiter " + name + " wurde hinzugefügt");
                    textField.clear();
                    try {
                        DatenbankManager dbManager = new DatenbankManager();
                        dbManager.connect();
                        dbManager.saveComboBoxItems("Mitarbeiter", "mitarbeiterName", mitarbeiterList);
                        dbManager.disconnect();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        // Handle the exception properly
                    }
                }
            } else {
                confirmationLabel.setText("Bitte geben Sie einen Namen ein");
            }
        });

        // Überprüfen Sie, ob die Mitarbeiterliste noch keine Daten enthält
        if (mitarbeiterList.isEmpty()) {
            try {
                // Verbindung zur Datenbank herstellen
                DatenbankManager dbManager = new DatenbankManager();
                // Verbindung zur Datenbank herstellen
                dbManager.connect();

                // Daten aus der Datenbank in die Mitarbeiterliste laden
                loadComboBoxItemsFromDatabase(dbManager);

                // Verbindung zur Datenbank trennen
                dbManager.disconnect();

            } catch (SQLException ex) {
                ex.printStackTrace();
                // Handle the exception properly
            }
        }

        // Erstellen Sie ein VBox-Layout
        VBox layout = new VBox(10);
        // Zentrieren Sie das Layout
        layout.setAlignment(Pos.CENTER);
        // Fügen Sie das Bestätigungs-Label, Textfeld und den Speichern-Button zum Layout hinzu
        layout.getChildren().addAll(label, textField, saveBtn, confirmationLabel,
                mitarbeiterLabel, comboBox, deleteBtn);

        // Erstellen Sie eine Szene
        Scene scene = new Scene(layout, 300, 300);
        // Setze die Szene
        stage.setScene(scene);
        // Zeige das Fenster
        stage.show();
        // Setze das Fenster auf 'true', wenn es geöffnet ist
        mitarbeiterWindow = stage;
        // Setze das Fenster auf 'true', wenn es geöffnet ist
        isMitarbeiterWindowOpen = true;

        // Setze die Aktionen, die ausgeführt werden, wenn das Fenster geschlossen wird
        stage.setOnCloseRequest(event -> {
            isMitarbeiterWindowOpen = false; // Setzen Sie den Status auf 'false', wenn das Fenster geschlossen wird
        });
    }


    // Diese Methode öffnet das Fenster zum Hinzufügen einer Schicht
    public void openAddSchichtWindow() {
        // Überprüfen Sie, ob das Fenster bereits geöffnet ist
        if (isSchichtWindowOpen) {
            // Wenn das Fenster bereits geöffnet ist, bringen Sie es nach vorne
            schichtWindow.toFront();
            return; // If the window is already open, terminate the method
        }

        // Erstellt ein neues Fenster
        Stage stage = new Stage();
        // Setze die Eigenschaften des Fensters
        stage.setTitle("Schicht hinzufügen");

        // Erstelle ein GridPane
        Button saveBtn = new Button("Speichern");
        // Definiere die Aktionen für den Speichern-Button
        Label label = new Label("Schichtname");
        // Erstellen Sie das Textfeld, in dem der Name des Mitarbeiters eingegeben wird
        TextField textField = new TextField();
        // Begrenzen Sie die maximale Breite des Textfelds
        textField.setMaxWidth(100);

        // Create the label that will display the confirmation message
        Label confirmationLabel = new Label();

        // ComboBox with the added shifts
        Label schichtLabel = new Label("Bereits hinzugefügte Schichten");
        // ComboBox mit den hinzugefügten Schichten
        ComboBox<String> comboBox = new ComboBox<>(schichtList);

        //Buttom zum Löschen des ausgewählten Namens aus der ComboBox
        Button deleteBtn = new Button("Löschen");
        // Definiere die Aktionen für den Löschen-Button
        deleteBtn.setOnAction(event -> {
            //Hier wird der ausgewählte Name aus der ComboBox gelöscht
            String selectedName = comboBox.getSelectionModel().getSelectedItem();
            // Prüft, ob ein Name ausgewählt wurde
            if (selectedName != null) {
                // Löscht den ausgewählten Namen aus der Liste
                schichtList.remove(selectedName);
                // Setzt die Bestätigungsnachricht
                confirmationLabel.setText("Schicht " + selectedName + " wurde gelöscht");
            }
        });

        // Button zum Hinzufügen einer Schicht
        saveBtn.setOnAction(e -> {
            // Hier wird der Name der Schicht aus dem Textfeld gelesen
            String name = textField.getText();
            // Überprüfen Sie, ob ein Name eingegeben wurde
            if (!name.isEmpty()) {
                // Fügen Sie den Namen der Liste hinzu
                schichtList.add(name);
                // Setzen Sie die Bestätigungsnachricht
                confirmationLabel.setText("Schicht " + name + " wurde hinzugefügt");
                // Löschen Sie den Text aus dem Textfeld
                textField.clear();

                // Speichern Sie den Namen in der Datenbank
                try {
                    // Verbindung zur Datenbank herstellen durch den DatenbankManager
                    DatenbankManager dbManager  = new DatenbankManager();
                    // Verbindung zur Datenbank herstellen
                    dbManager.connect();

                    // Speichern der schichtList in die Datenbank
                    // Konvertiere ObservableList zu ArrayList vor dem Speichern
                    List<String> schichtListAsRegularList = new ArrayList<>(schichtList);
                    // Speichern der schichtList in die Datenbank
                    dbManager.saveComboBoxItems("Schicht", "schichtName",
                                                schichtListAsRegularList);

                    // Verbindung zur Datenbank trennen
                    dbManager.disconnect();

                    // Fehlersbfang
                } catch (SQLException ex) {
                    // Fehlerbehandlung
                    ex.printStackTrace();
                }
                // Überprüfen Sie, ob ein Name eingegeben wurde
            } else {
                // Setzen Sie die Bestätigungsnachricht
                confirmationLabel.setText("Bitte geben Sie einen Namen ein");
            }
        });

        // Erstellen Sie ein VBox-Layout
        VBox layout = new VBox(10);
        // Zentrieren Sie das Layout
        layout.setAlignment(Pos.CENTER);
        // Add the confirmation label, text field and save button to the layout
        layout.getChildren().addAll(label, textField, saveBtn, confirmationLabel, schichtLabel, comboBox, deleteBtn);

        // Erstellen Sie eine Szene
        Scene scene = new Scene(layout, 300, 300);
        // Setze die Szene
        stage.setScene(scene);
        // Zeige das Fenster
        stage.show();
        // Setze das Fenster auf 'true', wenn es geöffnet ist
        schichtWindow = stage;
        isSchichtWindowOpen = true;
        stage.setOnCloseRequest(event -> {
        //Setzt den Status auf 'false', wenn das Fenster geschlossen ist
            isSchichtWindowOpen = false;
        });
    }
    //Methoden zum Laden der Daten aus der Datenbank
    private void loadComboBoxItemsFromDatabase(DatenbankManager dbManager) throws SQLException {
        // Daten aus der Datenbank in die ObservableLists laden
        mitarbeiterList.setAll(dbManager.loadComboBoxItems("Mitarbeiter", "mitarbeiterName"));
        // Konvertiere ArrayList zu ObservableList
        schichtList.setAll(dbManager.loadComboBoxItems("Schicht", "schichtName"));
    }

}
