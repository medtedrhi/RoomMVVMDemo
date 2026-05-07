package com.example.roommvvmdemo.viewmodel; // Déclare le package de la couche ViewModel.
import android.app.Application; // Importe Application pour le constructeur AndroidViewModel.
import androidx.annotation.NonNull; // Importe NonNull pour signaler que l'application ne doit pas être nulle.
import androidx.lifecycle.AndroidViewModel; // Importe AndroidViewModel qui possède un contexte application.
import androidx.lifecycle.LiveData; // Importe LiveData pour exposer les notes à l'interface.
import com.example.roommvvmdemo.data.NoteRepository; // Importe le repository des notes.
import com.example.roommvvmdemo.data.local.Note; // Importe l'entité Note.
import java.util.List; // Importe List pour représenter plusieurs notes.
public class NoteViewModel extends AndroidViewModel { // Déclare le ViewModel qui prépare les données pour l'écran.
    private final NoteRepository repository; // Stocke le repository utilisé par le ViewModel.
    private final LiveData<List<Note>> allNotes; // Stocke les notes observables exposées à l'activité.
    public NoteViewModel(@NonNull Application application) { // Crée le ViewModel avec le contexte application.
        super(application); // Transmet l'application à AndroidViewModel.
        repository = new NoteRepository(application); // Crée le repository des notes.
        allNotes = repository.getAllNotes(); // Récupère le LiveData des notes depuis le repository.
    } // Termine le constructeur du ViewModel.
    public void insert(Note note) { // Demande l'ajout d'une note.
        repository.insert(note); // Transmet l'ajout au repository.
    } // Termine la méthode insert.
    public void delete(Note note) { // Demande la suppression d'une note.
        repository.delete(note); // Transmet la suppression au repository.
    } // Termine la méthode delete.
    public void deleteAllNotes() { // Demande la suppression de toutes les notes.
        repository.deleteAllNotes(); // Transmet la suppression globale au repository.
    } // Termine la méthode deleteAllNotes.
    public LiveData<List<Note>> getAllNotes() { // Donne accès aux notes observables.
        return allNotes; // Retourne le LiveData des notes.
    } // Termine la méthode getAllNotes.
} // Termine la classe NoteViewModel.
