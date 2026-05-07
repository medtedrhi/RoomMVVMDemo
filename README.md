# RoomMVVMDemo

RoomMVVMDemo est une application Android Java pour apprendre Room, MVVM, LiveData, ViewModel, RecyclerView et CardView avec une petite application de notes.

## Architecture utilisée : MVVM

Le projet sépare le code en couches simples pour rendre le rôle de chaque fichier plus clair.

- `MainActivity` : affiche l'écran, lit les champs de saisie, réagit aux clics et observe les notes.
- `NoteViewModel` : prépare les données pour l'écran et survit aux rotations simples.
- `NoteRepository` : centralise l'accès aux données et lance les écritures sur un thread d'arrière-plan.
- `NoteDao` : contient les requêtes Room pour insérer, supprimer et lire les notes.
- `NoteDatabase` : crée la base Room locale qui utilise SQLite en interne.
- `Note Entity` : représente une ligne de la table `notes_table`.
- `LiveData` : permet à l'interface de recevoir automatiquement les changements de la base.
- `RecyclerView` : affiche efficacement la liste des notes avec une carte par note.

## Flux des données

Pour écrire une note :

`MainActivity -> ViewModel -> Repository -> DAO -> Room/SQLite`

Pour afficher les notes :

`Room/SQLite -> DAO LiveData -> Repository -> ViewModel -> Activity -> RecyclerView`

## Pourquoi éviter Room sur le thread principal ?

Les opérations de base de données peuvent prendre du temps. Si elles s'exécutent sur le thread principal, l'interface peut se bloquer et l'application peut devenir lente. Pour cette raison, les méthodes `insert`, `delete` et `deleteAllNotes` utilisent `ExecutorService`.

## Pourquoi le ViewModel survit à la rotation ?

Android recrée souvent l'activité lors d'une rotation d'écran. Le `ViewModel` est conservé pendant ce changement de configuration, donc les données déjà chargées restent disponibles pour la nouvelle instance de l'activité.

## Limite du ViewModel

Le `ViewModel` ne remplace pas complètement la gestion de la mort du processus. Si Android tue le processus de l'application, le `ViewModel` disparaît aussi. Pour conserver un petit état d'interface dans ce scénario, on peut utiliser Saved State avec le ViewModel.

## Étapes de test manuel

1. Insérer trois notes.
2. Faire un clic long sur une note pour la supprimer.
3. Fermer et rouvrir l'application pour vérifier la persistance.
4. Tourner l'écran pour vérifier le comportement du ViewModel.
5. Cliquer sur supprimer toutes les notes pour vider la base.

Ce README ne contient aucune commande Gradle à exécuter.
