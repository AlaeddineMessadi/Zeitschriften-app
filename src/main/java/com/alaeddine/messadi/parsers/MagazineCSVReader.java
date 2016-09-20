package com.alaeddine.messadi.parsers;

import com.alaeddine.messadi.entities.Author;
import com.alaeddine.messadi.entities.Magazine;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MagazineCSVReader {

    private static final char DEFAULT_SEPARATOR = ';';

    //Magazine attributes
    private static final String Magazine_TITEL = "Titel";
    private static final String Magazine_ISBN_NUMBER = "ISBN-Nummer";
    private static final String Magazine_AUTHOR = "Autor";
    private static final String Magazine_RELEASE_DATE = "Erscheinungsdatum";

    //CSV file header
    private static final String[] FILE_HEADER_MAPPING = {Magazine_TITEL, Magazine_ISBN_NUMBER, Magazine_AUTHOR, Magazine_RELEASE_DATE};

    private String fileName;
    private List<Magazine> magazines;

    public MagazineCSVReader(String fileName, List<Author> authorList) {
        this.fileName = fileName;
        this.magazines = this.magazineList(authorList);
    }

    public List<Magazine> magazineList(List<Author> authorList) {
        FileReader fileReader = null;
        CSVParser csvFileParser = null;

        //Create the CSVFormat object with the header mapping
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER_MAPPING).withDelimiter(';').withFirstRecordAsHeader();
        //Create a new list of Magazines to be filled by CSV file data
        List<Magazine> Magazines = new ArrayList<Magazine>();

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

                //Create a new Magazine object and fill his data
                Magazine Magazine = new Magazine(record.get(Magazine_TITEL), record.get(Magazine_ISBN_NUMBER), record.get(Magazine_AUTHOR), record.get(Magazine_RELEASE_DATE),authorList);
                Magazines.add(Magazine);
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

        return Magazines;
    }

    public List<Magazine> getMagazines() {
        return magazines;
    }

    public void setMagazines(List<Magazine> magazines) {
        this.magazines = magazines;
    }

    @Override
    public String toString() {
        String result = "";
        for (Magazine magazine : magazines) {
            result += magazine.toString()+'\n';
        }

        return result;
    }
}
