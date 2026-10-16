package com.example.lab5;

public abstract class Note {
    private String title;
    private User author;

    public Note(String title, User author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public User getAuthor() {
        return author;
    }

    public abstract String getNoteType();
    public abstract String getContent();
}
