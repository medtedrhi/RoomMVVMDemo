package com.example.roommvvmdemo.data; // Déclare le package de la couche repository.
import android.app.Application; // Importe Application pour obtenir le contexte global.
import androidx.lifecycle.LiveData; // Importe LiveData pour exposer des données observables.
import com.example.roommvvmdemo.data.local.Note; // Importe l'entité Note.
import com.example.roommvvmdemo.data.local.NoteDao; // Importe le DAO des notes.
import com.example.roommvvmdemo.data.local.NoteDatabase; // Importe la base de données Room.
import java.util.List; // Importe List pour représenter plusieurs notes.
import java.util.concurrent.ExecutorService; // Importe ExecutorService pour travailler hors du thread principal.
import java.util.concurrent.Executors; // Importe Executors pour créer un executor simple.
public class NoteRepository { // Déclare le repository qui centralise l'accès aux données.
    private final NoteDao noteDao; // Stocke le DAO utilisé pour parler à Room.
    private final LiveData<List<Note>> allNotes; // Stocke la liste observable de toutes les notes.
    private final ExecutorService executorService; // Stocke l'executor utilisé pour les écritures en arrière-plan.
    public NoteRepository(Application application) { // Crée le repository avec le contexte application.
        NoteDatabase database = NoteDatabase.getInstance(application); // Récupère l'instance unique de la base Room.
        noteDao = database.noteDao(); // Récupère le DAO depuis la base de données.
        allNotes = noteDao.getAllNotes(); // Récupère le LiveData qui observe toutes les notes.
        executorService = Executors.newSingleThreadExecutor(); // Crée un seul thread pour les opérations d'écriture.
    } // Termine le constructeur du repository.
    public void insert(Note note) { // Expose l'ajout d'une note.
        executorService.execute(() -> noteDao.insert(note)); // Lance l'insertion hors du thread principal.
    } // Termine la méthode insert.
    public void delete(Note note) { // Expose la suppression d'une note.
        executorService.execute(() -> noteDao.delete(note)); // Lance la suppression hors du thread principal.
    } // Termine la méthode delete.
    public void deleteAllNotes() { // Expose la suppression de toutes les notes.
        executorService.execute(noteDao::deleteAllNotes); // Lance la suppression globale hors du thread principal.
    } // Termine la méthode deleteAllNotes.
    public LiveData<List<Note>> getAllNotes() { // Expose la liste observable des notes.
        return allNotes; // Retourne le LiveData déjà fourni par le DAO.
    } // Termine la méthode getAllNotes.
} // Termine la classe NoteRepository.
