package com.example.lab5;

import java.util.Date;

public class TextNote extends Note {
    private String text;

    public TextNote(String title, User author, Date createdDate, String text) {
        super(title, author, createdDate);
        this.text = text;
    }

    @Override
    public String getNoteType() {
        return "Text Note";
    }

    @Override
    public String getContent() {
        return text;
    }
}
