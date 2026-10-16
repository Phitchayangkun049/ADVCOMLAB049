package com.example.lab5;

import java.util.Date;
import java.util.List;

public class CheckListNote extends Note {
    private List<String> items;

    public CheckListNote(String title, User author, Date createdDate, List<String> items) {
        super(title, author, createdDate);
        this.items = items;
    }

    public List<String> getItems() {
        return items;
    }

    @Override
    public String getNoteType() {
        return "CheckList Note";
    }

    @Override
    public String getContent() {
        return items != null ? String.join(", ", items) : "";
    }
}
