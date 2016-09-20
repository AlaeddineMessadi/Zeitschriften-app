package com.alaeddine.messadi.parsers;

import com.alaeddine.messadi.entities.Author;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AuthorCSVReader {

    private static final char DEFAULT_SEPARATOR = ';';

    //AUTHOR attributes
    private static final String AUTHOR_EMAIL = "Emailadresse";
    private static final String AUTHOR_FNAME = "Vorname";
    private static final String AUTHOR_LNAME = "Nachname";

    //CSV file header
    private static final String[] FILE_HEADER_MAPPING = {AUTHOR_EMAIL, AUTHOR_FNAME, AUTHOR_LNAME};

    private String fileName;
    private List<Author> authorList = new ArrayList<>();

    public AuthorCSVReader(String fileName) {
        this.fileName = fileName;
    }

    public List<Author> authorList() {
        FileReader fileReader = null;
        CSVParser csvFileParser = null;

        //Create the CSVFormat object with the header mapping
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER_MAPPING).withDelimiter(DEFAULT_SEPARATOR).withFirstRecordAsHeader();
        //Create a new list of Authors to be filled by CSV file data
        List<Author> authors = new ArrayList<>();

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

                //Create a new AUTHOR object and fill his data
                Author author = new Author(record.get(AUTHOR_EMAIL), record.get(AUTHOR_FNAME), record.get(AUTHOR_LNAME));
                authors.add(author);
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

        return authors;
    }

    @Override
    public String toString() {
        String result = "";
        for (Author author : this.authorList) {
            result += author.toString() + '\n';
        }
        return result;
    }

}
