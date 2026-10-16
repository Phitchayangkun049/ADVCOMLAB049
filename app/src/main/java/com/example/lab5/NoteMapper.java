package com.example.lab5;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class NoteMapper {

    static Gson gson = new Gson();

    // OOP -> Entity
    public static NoteEntity toEntity(Note note) {
        String userName = note.getAuthor() != null ? note.getAuthor().getName() : "Unknown";
        if (note instanceof TextNote) {
            return new NoteEntity(note.getTitle(), "text", null, ((TextNote) note).getContent(), note.getCreatedDate(), userName);
        } 
        else if (note instanceof CheckListNote) {
            String jsonItems = gson.toJson(((CheckListNote) note).getItems());
            return new NoteEntity(note.getTitle(), "checklist", jsonItems, null, note.getCreatedDate(), userName);
        }
        return null;
    }

    // Entity -> OOP
    public static Note fromEntity(NoteEntity entity) {
        User user = new User(entity.userName != null ? entity.userName : "Unknown");
        if (entity.type.equals("text")) {
            return new TextNote(entity.title, user, entity.createdDate, entity.content);
        } 
        else if (entity.type.equals("checklist")) {
            List<String> items = gson.fromJson(entity.checklistItemsJson, new TypeToken<List<String>>(){}.getType());
            return new CheckListNote(entity.title, user, entity.createdDate, items);
        }
        return null;
    }
}
