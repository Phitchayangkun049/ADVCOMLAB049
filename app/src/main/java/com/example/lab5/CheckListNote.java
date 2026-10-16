package com.example.lab5;

import java.util.List;

public class CheckListNote extends Note {
    private List<String> items;

    public CheckListNote(String title, User author, List<String> items) {
        super(title, author);
        this.items = items;
    }

    @Override
    public String getNoteType() {
        return "CheckList Note";
    }

    @Override
    public String getContent() {
        return String.join(", ", items);
    }
}
