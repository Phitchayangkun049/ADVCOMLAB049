package com.example.lab5;

import java.util.Date;

public abstract class Note {
    private String title;
    private User author;
    private Date createdDate;

    public Note(String title, User author, Date createdDate) {
        this.title = title;
        this.author = author;
        this.createdDate = createdDate;
    }

    public String getTitle() {
        return title;
    }

    public User getAuthor() {
        return author;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public abstract String getNoteType();
    public abstract String getContent();

    public String display() {
        return "[" + getNoteType() + "] " + getTitle() + " by " + (author != null ? author.getName() : "Unknown") + ": " + getContent();
    }
}
