package com.example.andreimaslennikov.notes.Models;

public class Note {
    public static final String TABLE_NAME = "Notes";
    public static final String ID_COL = "id";
    public static final String TITLE_COL = "title";
    public static final String TEXT_COL = "text";

    private int id;
    private String title;
    private String text;

    public Note(int id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
