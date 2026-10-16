package com.example.lab5;

public class TextNote extends Note {
    private String text;

    public TextNote(String title, User author, String text) {
        super(title, author);
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
