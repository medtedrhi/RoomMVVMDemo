package com.example.roommvvmdemo.data.local; // Déclare le package de la classe Note.
import androidx.room.Entity; // Importe l'annotation Entity de Room.
import androidx.room.PrimaryKey; // Importe l'annotation PrimaryKey de Room.
@Entity(tableName = "notes_table") // Indique que cette classe représente la table notes_table.
public class Note { // Déclare la classe Note qui représente une note.
    @PrimaryKey(autoGenerate = true) // Demande à Room de générer automatiquement l'identifiant.
    private int id; // Stocke l'identifiant unique de la note.
    private String title; // Stocke le titre de la note.
    private String description; // Stocke la description de la note.
    public Note(String title, String description) { // Crée une note avec un titre et une description.
        this.title = title; // Enregistre le titre reçu dans l'objet.
        this.description = description; // Enregistre la description reçue dans l'objet.
    } // Termine le constructeur de la note.
    public int getId() { // Fournit l'identifiant de la note.
        return id; // Retourne l'identifiant actuel.
    } // Termine la méthode getId.
    public void setId(int id) { // Permet à Room de définir l'identifiant généré.
        this.id = id; // Enregistre le nouvel identifiant dans l'objet.
    } // Termine la méthode setId.
    public String getTitle() { // Fournit le titre de la note.
        return title; // Retourne le titre actuel.
    } // Termine la méthode getTitle.
    public String getDescription() { // Fournit la description de la note.
        return description; // Retourne la description actuelle.
    } // Termine la méthode getDescription.
} // Termine la classe Note.
