package com.example.noteapp.models;

public class Note {

    private Integer id;
    private String title;
    private String text;
    private Integer priority;
    private boolean hasBeenRead;

    public Note() {

    }

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
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

    public Integer getPriority() {
        return priority;
    }


    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public boolean getHasBeenRead() {
        return hasBeenRead;
    }

    public void setHasBeenRead(boolean read) {
        this.hasBeenRead = read;
    }
}
