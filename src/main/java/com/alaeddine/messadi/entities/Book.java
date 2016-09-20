package com.alaeddine.messadi.entities;

import java.util.List;

public class Book extends Edition {
    private String summary;

    public Book(String title, String isbnNUmber, String authors, String summary, List<Author> authorList) {
        super(title, isbnNUmber, authors, authorList);
        this.summary = summary;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "Book{ " + super.toString() +
                "| summary = '" + this.summary + '\'' +
                '}';
    }
}
