package com.example.todoapp;

public class Tasks {

    private String text;
    private String date;

    Tasks(String text, String date)
    {
        this.text = text;
        this.date = date;
    }

    public Tasks() {

    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
