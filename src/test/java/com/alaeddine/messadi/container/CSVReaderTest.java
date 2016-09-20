package com.alaeddine.messadi.container;

import junit.framework.TestCase;

public class CSVReaderTest extends TestCase {
    private static final String AUTHOR_CSV = "autoren.csv";
    private static final String MAGAZINE_CSV = "zeitschriften.csv";
    private static final String BOOK_CSV = "buecher.csv";

    private CSVReader csvReader;

    public void setUp() throws Exception {
        super.setUp();
        this.csvReader = new CSVReader(AUTHOR_CSV, MAGAZINE_CSV, BOOK_CSV);
    }

    public void testGetAuthorList() throws Exception {
        assertNotNull(csvReader.getAuthorList());
        assertEquals(6, csvReader.getAuthorList().size());
    }

    public void testGetMagazineList() throws Exception {
        assertNotNull(csvReader.getMagazineList());
        assertEquals(6, csvReader.getMagazineList().size());

    }

    public void testGetBookList() throws Exception {
        assertNotNull(csvReader.getBookList());
        assertEquals(8, csvReader.getBookList().size());
    }

}