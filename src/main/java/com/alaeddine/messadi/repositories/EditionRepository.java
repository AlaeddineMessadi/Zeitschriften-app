package com.alaeddine.messadi.repositories;

import com.alaeddine.messadi.container.CSVReader;
import com.alaeddine.messadi.entities.Book;
import com.alaeddine.messadi.entities.Edition;
import com.alaeddine.messadi.entities.Magazine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EditionRepository {
    private CSVReader csvContainer;

    public EditionRepository(CSVReader csvContainer) {
        this.csvContainer = csvContainer;
    }

    public List<Book> findAllBooks() {
        return this.csvContainer.getBookList();
    }

    public List<Magazine> findAllMagazines() {
        return this.csvContainer.getMagazineList();
    }

    public Edition findMagazineOrBookByIsnbNumber(String isbnNumber) {
        for (Book book : this.csvContainer.getBookList()) {
            if (book.getIsbnNUmber().equals(isbnNumber)) {
                return book;
            }
        }

        for (Magazine magazine : this.csvContainer.getMagazineList()) {
            if (magazine.getIsbnNUmber().equals(isbnNumber)) {
                return magazine;
            }
        }
        return null;
    }

    public List<Edition> findMagazinesAndBooksByAuthor(String isbnNumber) {
        List<Edition> editions = new ArrayList<>();

        for (Book book : this.csvContainer.getBookList()) {
            if (book.containsAuthor(isbnNumber)) {
                editions.add(book);
            }
        }

        for (Magazine magazine : this.csvContainer.getMagazineList()) {
            if (magazine.containsAuthor(isbnNumber)) {
                editions.add(magazine);
            }
        }
        return editions;
    }

    public List<Edition> findAllMagazineAndBooks() {
        return Stream.concat(this.findAllBooks().stream(), this.findAllMagazines().stream())
                .collect(Collectors.toList());
    }

    public List<Edition> findAllSortedMagazineAndBooksByTitle() {
        List<Edition> result = Stream.concat(this.findAllBooks().stream(), this.findAllMagazines().stream())
                .collect(Collectors.toList());

        // Sort Books and Magazines
        Collections.sort(result, (Edition a1, Edition a2) -> a1.getTitle().compareTo(a2.getTitle()));
        return result;
    }


    public List<Book> findAllSortedBooksByTitle() {
        List<Book> bookList = this.csvContainer.getBookList();
        // Sort Books and Magazines
        Collections.sort(bookList, (Edition a1, Edition a2) -> a1.getTitle().compareTo(a2.getTitle()));
        return bookList;
    }

    public List<Magazine> findAllSortedMagazinesByTitle() {
        List<Magazine> magazineList = this.csvContainer.getMagazineList();
        // Sort  Magazines
        Collections.sort(magazineList, (Edition a1, Edition a2) -> a1.getTitle().compareTo(a2.getTitle()));
        return magazineList;
    }

    public List<Magazine> findAllSortedMagazinesByDate() {
        List<Magazine> magazineList = this.csvContainer.getMagazineList();
        // Sort  Magazines
        Collections.sort(magazineList, (Magazine a1, Magazine a2) -> a1.getReleaseDate().compareTo(a2.getReleaseDate()));
        return magazineList;
    }

}
