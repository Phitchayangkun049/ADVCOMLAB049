package com.example.lab5;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;

public class NoteController {
    private List<Note> notes;

    public NoteController() {
        this.notes = new ArrayList<>();
    }

    public Note addTextNote(String title, String userName, String text) {
        User user = new User(userName);
        Note note = new TextNote(title, user, new Date(), text);
        notes.add(note);
        return note;
    }

    public Note addCheckListNote(String title, String userName, List<String> items) {
        User user = new User(userName);
        Note note = new CheckListNote(title, user, new Date(), items);
        notes.add(note);
        return note;
    }

    public String getNoteDetails(Note note) {
        if (note == null) return "";
        return "Author: " + note.getAuthor().getName() + "\n" +
                "Type: " + note.getNoteType() + "\n" +
                "Title: " + note.getTitle() + "\n" +
                "Created: " + note.getCreatedDate().toString() + "\n" +
                "Content: " + note.getContent();
    }

    // Integrated database saving logic
    public void saveNoteToDatabase(Context context, Note note) {
        // OOP -> entity
        NoteEntity entity = NoteMapper.toEntity(note);

        if (entity == null) return;

        // add data to db using background thread
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                // save note to Database 
                AppDatabase.getInstance(context).noteDao().insert(entity);

                // alert if Insert successful 
                Log.d("DatabaseAction", "Insert successful: Note saved to database.");

            } catch (Exception e) {
                // alert if Error inserting 
                Log.e("DatabaseAction", "Error inserting note: " + e.getMessage());
            }
        });
    }

    public interface LoadNotesCallback {
        void onNotesLoaded(List<Note> notes);
    }

    public void loadNotesFromDatabase(Context context, LoadNotesCallback callback) {
        Executors.newSingleThreadExecutor().execute(() -> {
            List<NoteEntity> entities = AppDatabase.getInstance(context).noteDao().getAll();
            List<Note> notesList = new ArrayList<>();
            for (NoteEntity entity : entities) {
                notesList.add(NoteMapper.fromEntity(entity));
            }
            callback.onNotesLoaded(notesList);
        });
    }
}
