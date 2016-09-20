package com.alaeddine.messadi.container;


import com.alaeddine.messadi.entities.Author;
import com.alaeddine.messadi.entities.Book;
import com.alaeddine.messadi.entities.Magazine;
import com.alaeddine.messadi.parsers.AuthorCSVReader;
import com.alaeddine.messadi.parsers.BookCSVReader;
import com.alaeddine.messadi.parsers.MagazineCSVReader;

import java.nio.file.Paths;
import java.util.List;

public class CSVReader {
    private static final String DATA_DIRACTORY = Paths.get(".").toAbsolutePath().normalize().toString() + "/data/";

    private List<Author> authorList;
    private List<Magazine> magazineList;
    private List<Book> bookList;

    public CSVReader(String authorCsv, String magazineCsv, String bookCsv) {

        AuthorCSVReader authorCSVReader = new AuthorCSVReader(DATA_DIRACTORY.concat(authorCsv));
        List<Author> authors = authorCSVReader.authorList();
        MagazineCSVReader magazineCSVReader = new MagazineCSVReader(DATA_DIRACTORY.concat(magazineCsv), authors);
        BookCSVReader bookCSVReader = new BookCSVReader(DATA_DIRACTORY.concat(bookCsv), authors);

        this.authorList = authors;
        this.magazineList = magazineCSVReader.magazineList(authors);
        this.bookList = bookCSVReader.bookList(authors);
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

    public List<Magazine> getMagazineList() {
        return magazineList;
    }

    public void setMagazineList(List<Magazine> magazineList) {
        this.magazineList = magazineList;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
