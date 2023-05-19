//Package name
package com.example.shiftplaner.model;
//Diese Klasse dient zur Erstellung der Schicht-Objekte
public class Schicht {
    //Variablen für die Schicht-Objekte
    private String name;
    //Konstruktor für die Schicht-Objekte
    private String zeit;
    //Konstruktor für die Schicht-Objekte
    public Schicht(String name) {
        this.name = name;
        this.zeit = zeit;
    }
    //Getter und Setter für die Variablen der Schicht-Objekte
    public String getName() {
        return name;
    }
    //Getter und Setter für die Variablen der Schicht-Objekte
    public String getZeit() {
        return zeit;
    }

    @Override
    //Methode zur Ausgabe der Schicht-Objekte
    public String toString() {
        return name + "\n" + zeit;
    }
}