package com.example.roommvvmdemo.ui; // Déclare le package de l'activité principale.
import android.os.Bundle; // Importe Bundle pour recevoir l'état de création de l'activité.
import android.widget.Button; // Importe Button pour utiliser les boutons.
import android.widget.EditText; // Importe EditText pour saisir du texte.
import android.widget.Toast; // Importe Toast pour afficher de courts messages.
import androidx.appcompat.app.AppCompatActivity; // Importe AppCompatActivity pour une activité compatible.
import androidx.lifecycle.ViewModelProvider; // Importe ViewModelProvider pour obtenir le ViewModel.
import androidx.recyclerview.widget.LinearLayoutManager; // Importe LinearLayoutManager pour afficher une liste verticale.
import androidx.recyclerview.widget.RecyclerView; // Importe RecyclerView pour afficher les notes.
import com.example.roommvvmdemo.R; // Importe R pour accéder aux ressources XML.
import com.example.roommvvmdemo.data.local.Note; // Importe l'entité Note.
import com.example.roommvvmdemo.viewmodel.NoteViewModel; // Importe le ViewModel des notes.
public class MainActivity extends AppCompatActivity { // Déclare l'activité principale de l'application.
    private NoteViewModel noteViewModel; // Stocke le ViewModel qui gère les notes.
    private EditText etTitle; // Stocke le champ de saisie du titre.
    private EditText etDescription; // Stocke le champ de saisie de la description.
    private Button btnAdd; // Stocke le bouton d'ajout.
    private Button btnDeleteAll; // Stocke le bouton de suppression totale.
    private NoteAdapter adapter; // Stocke l'adapter du RecyclerView.
    @Override // Indique que cette méthode remplace celle de AppCompatActivity.
    protected void onCreate(Bundle savedInstanceState) { // Méthode appelée quand l'activité est créée.
        super.onCreate(savedInstanceState); // Appelle le comportement normal de création de l'activité.
        setContentView(R.layout.activity_main); // Associe le layout principal à l'activité.
        etTitle = findViewById(R.id.etTitle); // Relie le champ titre du XML au code Java.
        etDescription = findViewById(R.id.etDescription); // Relie le champ description du XML au code Java.
        btnAdd = findViewById(R.id.btnAdd); // Relie le bouton d'ajout du XML au code Java.
        btnDeleteAll = findViewById(R.id.btnDeleteAll); // Relie le bouton de suppression totale du XML au code Java.
        RecyclerView recyclerView = findViewById(R.id.recyclerView); // Relie le RecyclerView du XML au code Java.
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Configure le RecyclerView en liste verticale.
        recyclerView.setHasFixedSize(true); // Optimise la liste car la taille des lignes reste stable.
        adapter = new NoteAdapter(); // Crée l'adapter qui affichera les notes.
        recyclerView.setAdapter(adapter); // Branche l'adapter sur le RecyclerView.
        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class); // Récupère le ViewModel lié à cette activité.
        noteViewModel.getAllNotes().observe(this, notes -> adapter.setNotes(notes)); // Observe LiveData et met à jour la liste automatiquement.
        btnAdd.setOnClickListener(v -> saveNote()); // Lance l'ajout d'une note quand le bouton est cliqué.
        btnDeleteAll.setOnClickListener(v -> noteViewModel.deleteAllNotes()); // Supprime toutes les notes quand le bouton est cliqué.
        adapter.setOnItemLongClickListener(note -> { // Définit l'action du clic long sur une note.
            noteViewModel.delete(note); // Supprime la note sélectionnée via le ViewModel.
            Toast.makeText(this, "Note supprimée", Toast.LENGTH_SHORT).show(); // Affiche un message de confirmation.
        }); // Termine l'action du clic long.
        adapter.setOnItemClickListener(note -> Toast.makeText(this, "Titre : " + note.getTitle(), Toast.LENGTH_SHORT).show()); // Affiche le titre de la note au clic simple.
    } // Termine la méthode onCreate.
    private void saveNote() { // Déclare la méthode qui ajoute une note.
        String title = etTitle.getText().toString().trim(); // Lit et nettoie le titre saisi.
        String description = etDescription.getText().toString().trim(); // Lit et nettoie la description saisie.
        if (title.isEmpty() || description.isEmpty()) { // Vérifie si un champ est vide.
            Toast.makeText(this, "Remplir le titre et la description", Toast.LENGTH_SHORT).show(); // Demande à l'utilisateur de remplir les champs.
            return; // Arrête la méthode pour éviter d'ajouter une note incomplète.
        } // Termine la vérification des champs vides.
        Note note = new Note(title, description); // Crée une nouvelle note avec les textes saisis.
        noteViewModel.insert(note); // Envoie la note au ViewModel pour l'enregistrer.
        etTitle.setText(""); // Vide le champ du titre après l'ajout.
        etDescription.setText(""); // Vide le champ de la description après l'ajout.
        Toast.makeText(this, "Note ajoutée", Toast.LENGTH_SHORT).show(); // Affiche un message de confirmation.
    } // Termine la méthode saveNote.
} // Termine la classe MainActivity.
