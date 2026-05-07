package com.example.roommvvmdemo.data.local; // Déclare le package de la base de données locale.
import android.content.Context; // Importe Context pour accéder au contexte de l'application.
import androidx.room.Database; // Importe l'annotation Database de Room.
import androidx.room.Room; // Importe la classe Room qui construit la base.
import androidx.room.RoomDatabase; // Importe la classe de base des bases Room.
@Database(entities = {Note.class}, version = 1, exportSchema = false) // Déclare la base Room avec l'entité Note et la version 1.
public abstract class NoteDatabase extends RoomDatabase { // Déclare la base de données qui hérite de RoomDatabase.
    private static volatile NoteDatabase instance; // Garde une seule instance partagée de la base.
    public abstract NoteDao noteDao(); // Donne accès au DAO des notes.
    public static NoteDatabase getInstance(Context context) { // Fournit l'instance unique de la base.
        if (instance == null) { // Vérifie si la base n'a pas encore été créée.
            synchronized (NoteDatabase.class) { // Protège la création contre les accès simultanés.
                if (instance == null) { // Revérifie l'instance après l'entrée dans le bloc synchronisé.
                    instance = Room.databaseBuilder(context.getApplicationContext(), NoteDatabase.class, "notes_database") // Prépare la base Room avec le contexte application et son nom.
                            .fallbackToDestructiveMigration() // Accepte de recréer la base si le schéma change.
                            // Attention : fallbackToDestructiveMigration convient pour un laboratoire ou une démo, mais pas pour une application de production.
                            .build(); // Construit réellement l'instance de la base.
                } // Termine la création conditionnelle de l'instance.
            } // Termine le bloc synchronisé.
        } // Termine la vérification de l'instance.
        return instance; // Retourne l'instance unique de la base.
    } // Termine la méthode getInstance.
} // Termine la classe NoteDatabase.
