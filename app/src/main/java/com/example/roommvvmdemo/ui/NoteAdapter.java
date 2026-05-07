package com.example.roommvvmdemo.ui; // Déclare le package de l'adapter de l'interface.
import android.view.LayoutInflater; // Importe LayoutInflater pour créer les vues XML.
import android.view.View; // Importe View pour gérer les clics.
import android.view.ViewGroup; // Importe ViewGroup pour recevoir le parent du layout.
import android.widget.TextView; // Importe TextView pour afficher le texte d'une note.
import androidx.annotation.NonNull; // Importe NonNull pour documenter les paramètres obligatoires.
import androidx.recyclerview.widget.RecyclerView; // Importe RecyclerView pour afficher une liste performante.
import com.example.roommvvmdemo.R; // Importe R pour accéder aux ressources XML.
import com.example.roommvvmdemo.data.local.Note; // Importe l'entité Note affichée dans la liste.
import java.util.ArrayList; // Importe ArrayList pour créer une liste vide au départ.
import java.util.List; // Importe List pour stocker les notes.
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> { // Déclare l'adapter qui relie les notes au RecyclerView.
    private List<Note> notes = new ArrayList<>(); // Stocke les notes affichées dans la liste.
    private OnItemClickListener clickListener; // Stocke l'écouteur du clic simple.
    private OnItemLongClickListener longClickListener; // Stocke l'écouteur du clic long.
    public interface OnItemClickListener { // Déclare l'interface du clic simple.
        void onItemClick(Note note); // Signale qu'une note a été cliquée.
    } // Termine l'interface du clic simple.
    public interface OnItemLongClickListener { // Déclare l'interface du clic long.
        void onItemLongClick(Note note); // Signale qu'une note a reçu un clic long.
    } // Termine l'interface du clic long.
    @NonNull // Indique que le ViewHolder retourné ne sera pas nul.
    @Override // Indique que cette méthode remplace celle de RecyclerView.Adapter.
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // Crée une nouvelle vue pour une note.
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false); // Transforme note_item.xml en objet View.
        return new NoteHolder(itemView); // Retourne un ViewHolder qui contient cette vue.
    } // Termine la création du ViewHolder.
    @Override // Indique que cette méthode remplace celle de RecyclerView.Adapter.
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) { // Remplit une ligne avec les données d'une note.
        Note currentNote = notes.get(position); // Récupère la note correspondant à la position actuelle.
        holder.tvTitle.setText(currentNote.getTitle()); // Affiche le titre de la note.
        holder.tvDescription.setText(currentNote.getDescription()); // Affiche la description de la note.
    } // Termine le remplissage de la ligne.
    @Override // Indique que cette méthode remplace celle de RecyclerView.Adapter.
    public int getItemCount() { // Donne le nombre d'éléments à afficher.
        return notes.size(); // Retourne la taille actuelle de la liste.
    } // Termine la méthode getItemCount.
    public void setNotes(List<Note> notes) { // Remplace la liste des notes affichées.
        this.notes = notes; // Enregistre la nouvelle liste reçue.
        notifyDataSetChanged(); // Demande au RecyclerView de rafraîchir l'affichage.
    } // Termine la méthode setNotes.
    public void setOnItemClickListener(OnItemClickListener clickListener) { // Définit le comportement du clic simple.
        this.clickListener = clickListener; // Enregistre l'écouteur du clic simple.
    } // Termine la méthode setOnItemClickListener.
    public void setOnItemLongClickListener(OnItemLongClickListener longClickListener) { // Définit le comportement du clic long.
        this.longClickListener = longClickListener; // Enregistre l'écouteur du clic long.
    } // Termine la méthode setOnItemLongClickListener.
    class NoteHolder extends RecyclerView.ViewHolder { // Déclare le ViewHolder qui garde les vues d'une ligne.
        private final TextView tvTitle; // Stocke la TextView du titre.
        private final TextView tvDescription; // Stocke la TextView de la description.
        public NoteHolder(@NonNull View itemView) { // Crée un ViewHolder pour une vue de note.
            super(itemView); // Transmet la vue à la classe RecyclerView.ViewHolder.
            tvTitle = itemView.findViewById(R.id.tvTitle); // Trouve la TextView du titre dans le layout.
            tvDescription = itemView.findViewById(R.id.tvDescription); // Trouve la TextView de la description dans le layout.
            itemView.setOnClickListener(v -> { // Écoute le clic simple sur la note.
                int position = getBindingAdapterPosition(); // Récupère la position actuelle de la ligne.
                if (clickListener != null && position != RecyclerView.NO_POSITION) { // Vérifie que le clic est valide.
                    clickListener.onItemClick(notes.get(position)); // Envoie la note cliquée à l'activité.
                } // Termine la vérification du clic simple.
            }); // Termine l'écouteur du clic simple.
            itemView.setOnLongClickListener(v -> { // Écoute le clic long sur la note.
                int position = getBindingAdapterPosition(); // Récupère la position actuelle de la ligne.
                if (longClickListener != null && position != RecyclerView.NO_POSITION) { // Vérifie que le clic long est valide.
                    longClickListener.onItemLongClick(notes.get(position)); // Envoie la note à supprimer à l'activité.
                    return true; // Indique que le clic long a été consommé.
                } // Termine la vérification du clic long.
                return false; // Indique que rien n'a été traité si le clic long est invalide.
            }); // Termine l'écouteur du clic long.
        } // Termine le constructeur du ViewHolder.
    } // Termine la classe interne NoteHolder.
} // Termine la classe NoteAdapter.
