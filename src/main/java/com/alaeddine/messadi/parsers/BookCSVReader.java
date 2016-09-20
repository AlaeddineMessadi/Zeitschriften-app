package com.alaeddine.messadi.parsers;

import com.alaeddine.messadi.entities.Author;
import com.alaeddine.messadi.entities.Book;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookCSVReader {

    private static final char DEFAULT_SEPARATOR = ';';

    //Book attributes
    private static final String BOOK_TITEL = "Titel";
    private static final String BOOK_ISBN_NUMBER = "ISBN-Nummer";
    private static final String BOOK_AUTHOR = "Autoren";
    private static final String BOOK_RELEASE_DATE = "Kurzbeschreibung";

    //CSV file header
    private static final String[] FILE_HEADER_MAPPING = {BOOK_TITEL, BOOK_ISBN_NUMBER, BOOK_AUTHOR, BOOK_RELEASE_DATE};

    private String fileName;
    private List<Book> bookList;

    public BookCSVReader(String fileName, List<Author> authorList) {
        this.fileName = fileName;
        this.bookList = this.bookList(authorList);
    }

    public List<Book> bookList(List<Author> authorList) {
        FileReader fileReader = null;
        CSVParser csvFileParser = null;

        //Create the CSVFormat object with the header mapping
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER_MAPPING).withDelimiter(DEFAULT_SEPARATOR).withFirstRecordAsHeader();
        //Create a new list of bookList to be filled by CSV file data
        List<Book> books = new ArrayList<Book>();

        try {

            //initialize FileReader object
            fileReader = new FileReader(this.fileName);

            //initialize CSVParser object
            csvFileParser = new CSVParser(fileReader, csvFileFormat);

            //Get a list of CSV file records
            Iterator<CSVRecord> csvRecords = csvFileParser.iterator();

            //Read the CSV file records starting from the second record to skip the header
            for (; csvRecords.hasNext(); ) {
                CSVRecord record = csvRecords.next();

                //Create a new Book object and fill his data
                Book Book = new Book(record.get(BOOK_TITEL), record.get(BOOK_ISBN_NUMBER), record.get(BOOK_AUTHOR), record.get(BOOK_RELEASE_DATE), authorList);
                books.add(Book);
            }
        } catch (Exception e) {
            System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        } finally {
            try {
                if (fileReader != null)
                    fileReader.close();
                if (csvFileParser != null)
                    csvFileParser.close();
            } catch (IOException e) {
                System.out.println("Error while closing fileReader/csvFileParser !!!");
                e.printStackTrace();
            }
        }

        return books;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    @Override
    public String toString() {
        String result = "";
        for (Book book : this.bookList) {
            result += book.toString() + '\n';
        }

        return result;
    }
}
