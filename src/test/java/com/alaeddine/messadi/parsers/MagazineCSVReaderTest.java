package com.alaeddine.messadi.parsers;

import com.alaeddine.messadi.entities.Author;
import junit.framework.TestCase;

import java.nio.file.Paths;
import java.util.List;

public class MagazineCSVReaderTest extends TestCase {

    private static final String DATA_DIRACTORY = Paths.get(".").toAbsolutePath().normalize().toString() + "/data/";
    private static final String AUTHOR_CSV = "autoren.csv";
    private static final String MAGAZINE_CSV = "zeitschriften.csv";


    private List<Author> authors;

    public void setUp() throws Exception {
        super.setUp();
        this.authors = new AuthorCSVReader(DATA_DIRACTORY+AUTHOR_CSV).getAuthorList();
    }

    public void testGetMagazines() throws Exception {
        MagazineCSVReader magazineCSVReader = new MagazineCSVReader(DATA_DIRACTORY+MAGAZINE_CSV,authors);

        assertNotNull(magazineCSVReader.getMagazines());
        assertEquals(6,magazineCSVReader.getMagazines().size());
    }

    public void testToString() throws Exception {
        MagazineCSVReader magazineCSVReader = new MagazineCSVReader(DATA_DIRACTORY+MAGAZINE_CSV,authors);

        String result = magazineCSVReader.toString();
        String expected = "Magazine{ title='Sch�ner kochen'| isbnNUmber = '5454-5587-3210'| authors = pr-walter@optivo.de| releaseDate = 21.05.2006}\n" +
                "Magazine{ title='Meine Familie und ich'| isbnNUmber = '4545-8541-2012'| authors = pr-mueller@optivo.de| releaseDate = 10.07.2006}\n" +
                "Magazine{ title='Kochen f�r Genie�er'| isbnNUmber = '2365-5632-7854'| authors = pr-lieblich@optivo.de, pr-walter@optivo.de| releaseDate = 01.05.2007}\n" +
                "Magazine{ title='Gourmet'| isbnNUmber = '2365-8745-7854'| authors = pr-ferdinand@optivo.de| releaseDate = 14.06.2005}\n" +
                "Magazine{ title='Der Weinkenner'| isbnNUmber = '2547-8548-2541'| authors = pr-walter@optivo.de| releaseDate = 12.12.2002}\n" +
                "Magazine{ title='Vinum'| isbnNUmber = '1313-4545-8875'| authors = pr-gustafsson@optivo.de| releaseDate = 23.02.2004}\n";
        assertEquals(expected,result);
    }

}