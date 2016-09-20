package com.alaeddine.messadi.repositories;

import com.alaeddine.messadi.container.CSVReader;
import com.alaeddine.messadi.entities.Author;

import java.util.List;

public class AuthorRepository {

    private CSVReader csvContainer;

    public AuthorRepository(CSVReader csvContainer) {
        this.csvContainer = csvContainer;
    }

    public List<Author> findAllAuthors() {
        return this.csvContainer.getAuthorList();
    }

    public Author findAuthorByEmail(String email) {
        for (Author author : this.csvContainer.getAuthorList()) {
            if (author.getEmail().equals(email)) {
                return author;
            }
        }
        return null;
    }

    public Author findAuthorByFirstName(String firstName) {
        for (Author author : this.csvContainer.getAuthorList()) {
            if (author.getFirstName().equals(firstName)) {
                return author;
            }
        }
        return null;
    }

    public Author findAuthorByLastName(String lastName) {
        for (Author author : this.csvContainer.getAuthorList()) {
            if (author.getLastName().equals(lastName)) {
                return author;
            }
        }
        return null;
    }
}
