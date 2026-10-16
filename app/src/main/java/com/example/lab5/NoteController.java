package com.example.lab5;

import java.util.ArrayList;
import java.util.List;

public class NoteController {
    private List<Note> notes;

    public NoteController() {
        this.notes = new ArrayList<>();
    }

    public Note addTextNote(String title, String userName, String text) {
        User user = new User(userName);
        Note note = new TextNote(title, user, text);
        notes.add(note);
        return note;
    }

    public Note addCheckListNote(String title, String userName, List<String> items) {
        User user = new User(userName);
        Note note = new CheckListNote(title, user, items);
        notes.add(note);
        return note;
    }

    public String getNoteDetails(Note note) {
        if (note == null) return "";
        return "Author: " + note.getAuthor().getName() + "\n" +
                "Type: " + note.getNoteType() + "\n" +
                "Title: " + note.getTitle() + "\n" +
                "Content: " + note.getContent();
    }
}
