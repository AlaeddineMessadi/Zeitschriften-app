package com.alaeddine.messadi.entities;

import java.util.List;

public class Magazine extends Edition {

    private String releaseDate;

    public Magazine(String title, String isbnNUmber, String authors, String releaseDate, List<Author> authorList) {
        super(title, isbnNUmber, authors, authorList);
        this.releaseDate = releaseDate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Magazine{ " + super.toString() +
                "| releaseDate = " + releaseDate +
                '}';
    }
}
