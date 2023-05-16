package com.example.shiftplaner.model;

public class Schicht {
    private String name;
    private String zeit;

    public Schicht(String name) {
        this.name = name;
        this.zeit = zeit;
    }

    public String getName() {
        return name;
    }

    public String getZeit() {
        return zeit;
    }

    @Override
    public String toString() {
        return name + "\n" + zeit;
    }
}