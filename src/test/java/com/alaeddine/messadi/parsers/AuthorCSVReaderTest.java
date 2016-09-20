package com.alaeddine.messadi.parsers;

import com.alaeddine.messadi.container.CSVReader;
import junit.framework.TestCase;

import java.nio.file.Paths;

public class AuthorCSVReaderTest extends TestCase {
    private static final String DATA_DIRACTORY = Paths.get(".").toAbsolutePath().normalize().toString() + "/data/";
    private static final String AUTHOR_CSV = "autoren.csv";

    public void testAuthorList() throws Exception {
        AuthorCSVReader authorCSVReader = new AuthorCSVReader(DATA_DIRACTORY+AUTHOR_CSV);
        assertNotNull(authorCSVReader.getAuthorList());
        assertEquals(6,authorCSVReader.getAuthorList().size());
    }

    public void testToString() throws Exception {
        AuthorCSVReader authorCSVReader = new AuthorCSVReader(DATA_DIRACTORY+AUTHOR_CSV);
        String result = authorCSVReader.toString();
        String expected = "Author{email='pr-walter@optivo.de', firstName='Paul', lastName='Walter'}\n" +
                "Author{email='pr-mueller@optivo.de', firstName='Max', lastName='M�ller'}\n" +
                "Author{email='pr-ferdinand@optivo.de', firstName='Franz', lastName='Ferdinand'}\n" +
                "Author{email='pr-gustafsson@optivo.de', firstName='Karl', lastName='Gustafsson'}\n" +
                "Author{email='pr-lieblich@optivo.de', firstName='Werner', lastName='Lieblich'}\n" +
                "Author{email='pr-rabe@optivo.de', firstName='Harald', lastName='Rabe'}\n";
        assertEquals(expected,result);
    }

}