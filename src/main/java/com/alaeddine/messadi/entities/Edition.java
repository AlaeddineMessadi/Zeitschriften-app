package com.alaeddine.messadi.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Edition {

    private String title;
    private String isbnNUmber;
    private List<Author> authors = new ArrayList<>();

    public Edition(String title, String isbnNUmber, String authorEmail, List<Author> authorList) {
        this.title = title;
        this.isbnNUmber = isbnNUmber;
        this.authors = this.findAuthorByEmail(authorEmail, authorList);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbnNUmber() {
        return isbnNUmber;
    }

    public void setIsbnNUmber(String isbnNUmber) {
        this.isbnNUmber = isbnNUmber;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    private List<Author> findAuthorByEmail(String email, List<Author> authorList) {
        String[] parts = email.split(",");
        List<Author> result = new ArrayList<>();

        // find and add all Authors by email
        for (String authorEmail : parts) {
            Author author = authorList.stream()
                    .filter(a -> Objects.equals(a.getEmail(), authorEmail))
                    .findFirst().get();

            result.add(author);
        }
        if (result.isEmpty()) {
            return null;
        }

        return result;
    }

    public boolean containsAuthor(String str) {
        for (Author author : this.getAuthors()) {
            if ((author.getEmail() + author.getFirstName() + author.getLastName()).toLowerCase().contains(str.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String emails = "";
        int n = this.getAuthors().size();

        for (int i = 0; i < n; i++) {
            emails += this.getAuthors().get(i).getEmail();
            if (i != n - 1) {
                emails += ", ";
            }
        }

        return "title='" + title + '\'' +
                "| isbnNUmber = '" + isbnNUmber + '\'' +
                "| authors = " + emails;
    }
}
