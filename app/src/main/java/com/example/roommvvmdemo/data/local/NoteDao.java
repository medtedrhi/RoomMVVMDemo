package com.example.roommvvmdemo.data.local; // Déclare le package du DAO des notes.
import androidx.lifecycle.LiveData; // Importe LiveData pour observer les données automatiquement.
import androidx.room.Dao; // Importe l'annotation Dao de Room.
import androidx.room.Delete; // Importe l'annotation Delete de Room.
import androidx.room.Insert; // Importe l'annotation Insert de Room.
import androidx.room.Query; // Importe l'annotation Query de Room.
import java.util.List; // Importe List pour manipuler une liste de notes.
@Dao // Indique que cette interface contient les requêtes Room.
public interface NoteDao { // Déclare l'interface qui décrit les opérations sur les notes.
    @Insert // Demande à Room de créer le code SQL d'insertion.
    void insert(Note note); // Insère une note dans la base de données.
    @Delete // Demande à Room de créer le code SQL de suppression.
    void delete(Note note); // Supprime une note précise de la base de données.
    @Query("DELETE FROM notes_table") // Définit la requête SQL qui supprime toutes les notes.
    void deleteAllNotes(); // Supprime toutes les notes enregistrées.
    @Query("SELECT * FROM notes_table ORDER BY id DESC") // Définit la requête SQL qui récupère les notes récentes en premier.
    LiveData<List<Note>> getAllNotes(); // Retourne toutes les notes dans un LiveData observable.
} // Termine l'interface NoteDao.
