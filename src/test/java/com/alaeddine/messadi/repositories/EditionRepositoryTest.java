package com.alaeddine.messadi.repositories;

import com.alaeddine.messadi.container.CSVReader;
import com.alaeddine.messadi.entities.Book;
import com.alaeddine.messadi.entities.Edition;
import com.alaeddine.messadi.entities.Magazine;
import junit.framework.TestCase;

import java.nio.file.Paths;
import java.util.List;

public class EditionRepositoryTest extends TestCase {
    private static final String AUTHOR_CSV = "autoren.csv";
    private static final String MAGAZINE_CSV = "zeitschriften.csv";
    private static final String BOOK_CSV = "buecher.csv";

    private EditionRepository editionRepository;

    public void setUp() throws Exception {
        super.setUp();
        this.editionRepository = new EditionRepository(new CSVReader(AUTHOR_CSV, MAGAZINE_CSV, BOOK_CSV));
    }

    public void testFindAllBooks() throws Exception {

        List<Book> books = this.editionRepository.findAllBooks();
        assertNotNull(books);
        assertEquals(8, books.size());
    }

    public void testFindAllMagazines() throws Exception {

        List<Magazine> magazines = this.editionRepository.findAllMagazines();
        assertNotNull(magazines);
        assertEquals(6, magazines.size());
    }

    public void testFindAllMagazineAndBooks() throws Exception {

        List<Edition> allMagazineAndBooks = this.editionRepository.findAllMagazineAndBooks();
        assertNotNull(allMagazineAndBooks);
        assertEquals(14, allMagazineAndBooks.size());
    }

    public void testFindMagazineOrBookByIsnbNumber() throws Exception {
        Edition edition = this.editionRepository.findMagazineOrBookByIsnbNumber("5554-5545-45128"); // wrong isbn number
        assertNull(edition);
        edition = this.editionRepository.findMagazineOrBookByIsnbNumber("5554-5545-4518");
        assertNotNull(edition);
        assertEquals("5554-5545-4518", edition.getIsbnNUmber());
    }

    public void testFindMagazinesAndBooksByAuthor() throws Exception {
        List<Edition> editions = this.editionRepository.findMagazinesAndBooksByAuthor("pr-mueller@optivo.dee");
        assertNull(editions);
        editions = this.editionRepository.findMagazinesAndBooksByAuthor("pr-mueller@optivo.de");
        assertNotNull(editions);

    }

    public void testFindAllSortedMagazineAndBooksByTitle() throws Exception {
        List<Edition> editions = this.editionRepository.findAllSortedMagazineAndBooksByTitle();
        assertNotNull(editions);
        assertEquals(14, editions.size());
        String expected = "[Book{ title='Das Perfekte Dinner. Die besten Rezepte'| isbnNUmber = '2221-5548-8585'| authors = pr-lieblich@optivo.de| summary = 'Sie wollen auch ein perfektes Dinner kreieren? Mit diesem Buch gelingt es Ihnen!'}, Book{ title='Das Piratenkochbuch. Ein Spezialit�tenkochbuch mit den 150 leckersten Rezepten '| isbnNUmber = '3214-5698-7412'| authors = pr-rabe@optivo.de| summary = 'Das Piraten-Kochbuch beweist, dass die Seer�uberkapit�ne zwar gef�rchtete Haudegen waren, andererseits aber manches Mal mit gehobenenem Geschmacksempfinden ausgestattet. ... Kurzum, ein ideales Buch, um maritime Events kulinarisch zu umrahmen.'}, Book{ title='Das gro�e GU-Kochbuch Kochen f�r Kinder'| isbnNUmber = '2145-8548-3325'| authors = pr-ferdinand@optivo.de, pr-lieblich@optivo.de| summary = 'Es beginnt mit... den ersten L�ffelchen f�r Mami, Papi und den Rest der Welt. Ja, und dann? Was Hersteller von Babynahrung k�nnen, das ist oft im Handumdrehen auch selbst gemacht, vielleicht sogar ges�nder, oftmals frischer. �ltere Babys und Schulkinder will das Kochbuch ansprechen und das tut es auf eine verspielte Art angenehm altersgem��.'}, Magazine{ title='Der Weinkenner'| isbnNUmber = '2547-8548-2541'| authors = pr-walter@optivo.de| releaseDate = 12.12.2002}, Book{ title='Genial italienisch'| isbnNUmber = '1024-5245-8584'| authors = pr-lieblich@optivo.de, pr-walter@optivo.de, pr-rabe@optivo.de| summary = 'Starkoch Jamie Oliver war mit seinem VW-Bus in Italien unterwegs -- und hat allerlei kulinarische Souvenirs mitgebracht. Es lohnt sich, einen Blick in sein Gep�ck zu werfen...'}, Magazine{ title='Gourmet'| isbnNUmber = '2365-8745-7854'| authors = pr-ferdinand@optivo.de| releaseDate = 14.06.2005}, Book{ title='Ich helf dir kochen. Das erfolgreiche Universalkochbuch mit gro�em Backteil'| isbnNUmber = '5554-5545-4518'| authors = pr-walter@optivo.de| summary = 'Auf der Suche nach einem Basiskochbuch steht man heutzutage vor einer F�lle von Alternativen. Es f�llt schwer, daraus die f�r sich passende Mixtur aus Grundlagenwerk und Rezeptesammlung zu finden. Man sollte sich dar�ber im Klaren sein, welchen Schwerpunkt man setzen m�chte oder von welchen Koch- und Backkenntnissen man bereits ausgehen kann.'}, Magazine{ title='Kochen f�r Genie�er'| isbnNUmber = '2365-5632-7854'| authors = pr-lieblich@optivo.de, pr-walter@optivo.de| releaseDate = 01.05.2007}, Magazine{ title='Meine Familie und ich'| isbnNUmber = '4545-8541-2012'| authors = pr-mueller@optivo.de| releaseDate = 10.07.2006}, Book{ title='O'Reillys Kochbuch f�r Geeks'| isbnNUmber = '2215-0012-5487'| authors = pr-mueller@optivo.de| summary = 'Nach landl�ufiger Meinung leben Geeks von Cola und TK-Pizza, die sie nachts am Rechner geistesabwesend in sich reinschlingen. Soweit das Klischee! Dass aber Kochen viel mit Programmieren zu tun hat, dass es n�mlich �hnlich kreativ ist, dass viele Wege zum individuellen Ziel f�hren und dass manche Rezepte einfach nur abgefahren und -- ja, geekig sind: Das zeigen zwei K�chinnen in diesem Buch.'}, Book{ title='Schlank im Schlaf '| isbnNUmber = '4545-8558-3232'| authors = pr-gustafsson@optivo.de| summary = 'Schlank im Schlaf klingt wie ein sch�ner Traum, aber es ist wirklich m�glich. Allerdings nicht nach einer Salamipizza zum Abendbrot. Die Grundlagen dieses neuartigen Konzepts sind eine typgerechte Insulin-Trennkost sowie Essen und Sport im Takt der biologischen Uhr. Wie die Bio-Uhr tickt und was auf dem Speiseplan stehen sollte, h�ngt vom pers�nlichen Urtyp ab: Nomade oder Ackerbauer?'}, Book{ title='Schuhbecks Kochschule. Kochen lernen mit Alfons Schuhbeck '| isbnNUmber = '1215-4545-5895'| authors = pr-walter@optivo.de| summary = 'Aller Anfang ist leicht! Zumindest, wenn man beim Kochenlernen einen Lehrer wie Alfons Schuhbeck zur Seite hat. Mit seiner Hilfe kann auch der ungeschickteste Anf�nger beste Noten f�r seine Gerichte bekommen. Der Trick, den der vielfach ausgezeichnete Meisterkoch dabei anwendet, lautet visualisieren. Die einzelnen Arbeitsschritte werden auf Farbfotos im Format von ca. 3x4 cm abgebildet. Unter diesen stehen kurz und knapp die Angaben zur Zubereitung. So pr�sentiert Schuhbecks Kochschule alles bequem auf einen Blick. Und der interessierte Kochneuling kann sich auf die Hauptsache konzentrieren � aufs Kochen. Wie die Speise aussehen soll, zeigt jeweils das Farbfoto auf der linken Seite, auf der auch die Zutaten � dank des geschickten Layouts � ebenfalls sehr �bersichtlich aufgelistet sind. Au�erdem gibt Schuhbeck pr�gnante Empfehlungen zu Zutaten und Zubereitung.'}, Magazine{ title='Sch�ner kochen'| isbnNUmber = '5454-5587-3210'| authors = pr-walter@optivo.de| releaseDate = 21.05.2006}, Magazine{ title='Vinum'| isbnNUmber = '1313-4545-8875'| authors = pr-gustafsson@optivo.de| releaseDate = 23.02.2004}]";
        assertEquals(expected,editions.toString());

    }

    public void testFindAllSortedBooksByTitle() throws Exception {
        List<Book> books = this.editionRepository.findAllSortedBooksByTitle();
        assertNotNull(books);
        assertEquals(8,books.size());
        String expected = "[Book{ title='Das Perfekte Dinner. Die besten Rezepte'| isbnNUmber = '2221-5548-8585'| authors = pr-lieblich@optivo.de| summary = 'Sie wollen auch ein perfektes Dinner kreieren? Mit diesem Buch gelingt es Ihnen!'}, Book{ title='Das Piratenkochbuch. Ein Spezialit�tenkochbuch mit den 150 leckersten Rezepten '| isbnNUmber = '3214-5698-7412'| authors = pr-rabe@optivo.de| summary = 'Das Piraten-Kochbuch beweist, dass die Seer�uberkapit�ne zwar gef�rchtete Haudegen waren, andererseits aber manches Mal mit gehobenenem Geschmacksempfinden ausgestattet. ... Kurzum, ein ideales Buch, um maritime Events kulinarisch zu umrahmen.'}, Book{ title='Das gro�e GU-Kochbuch Kochen f�r Kinder'| isbnNUmber = '2145-8548-3325'| authors = pr-ferdinand@optivo.de, pr-lieblich@optivo.de| summary = 'Es beginnt mit... den ersten L�ffelchen f�r Mami, Papi und den Rest der Welt. Ja, und dann? Was Hersteller von Babynahrung k�nnen, das ist oft im Handumdrehen auch selbst gemacht, vielleicht sogar ges�nder, oftmals frischer. �ltere Babys und Schulkinder will das Kochbuch ansprechen und das tut es auf eine verspielte Art angenehm altersgem��.'}, Book{ title='Genial italienisch'| isbnNUmber = '1024-5245-8584'| authors = pr-lieblich@optivo.de, pr-walter@optivo.de, pr-rabe@optivo.de| summary = 'Starkoch Jamie Oliver war mit seinem VW-Bus in Italien unterwegs -- und hat allerlei kulinarische Souvenirs mitgebracht. Es lohnt sich, einen Blick in sein Gep�ck zu werfen...'}, Book{ title='Ich helf dir kochen. Das erfolgreiche Universalkochbuch mit gro�em Backteil'| isbnNUmber = '5554-5545-4518'| authors = pr-walter@optivo.de| summary = 'Auf der Suche nach einem Basiskochbuch steht man heutzutage vor einer F�lle von Alternativen. Es f�llt schwer, daraus die f�r sich passende Mixtur aus Grundlagenwerk und Rezeptesammlung zu finden. Man sollte sich dar�ber im Klaren sein, welchen Schwerpunkt man setzen m�chte oder von welchen Koch- und Backkenntnissen man bereits ausgehen kann.'}, Book{ title='O'Reillys Kochbuch f�r Geeks'| isbnNUmber = '2215-0012-5487'| authors = pr-mueller@optivo.de| summary = 'Nach landl�ufiger Meinung leben Geeks von Cola und TK-Pizza, die sie nachts am Rechner geistesabwesend in sich reinschlingen. Soweit das Klischee! Dass aber Kochen viel mit Programmieren zu tun hat, dass es n�mlich �hnlich kreativ ist, dass viele Wege zum individuellen Ziel f�hren und dass manche Rezepte einfach nur abgefahren und -- ja, geekig sind: Das zeigen zwei K�chinnen in diesem Buch.'}, Book{ title='Schlank im Schlaf '| isbnNUmber = '4545-8558-3232'| authors = pr-gustafsson@optivo.de| summary = 'Schlank im Schlaf klingt wie ein sch�ner Traum, aber es ist wirklich m�glich. Allerdings nicht nach einer Salamipizza zum Abendbrot. Die Grundlagen dieses neuartigen Konzepts sind eine typgerechte Insulin-Trennkost sowie Essen und Sport im Takt der biologischen Uhr. Wie die Bio-Uhr tickt und was auf dem Speiseplan stehen sollte, h�ngt vom pers�nlichen Urtyp ab: Nomade oder Ackerbauer?'}, Book{ title='Schuhbecks Kochschule. Kochen lernen mit Alfons Schuhbeck '| isbnNUmber = '1215-4545-5895'| authors = pr-walter@optivo.de| summary = 'Aller Anfang ist leicht! Zumindest, wenn man beim Kochenlernen einen Lehrer wie Alfons Schuhbeck zur Seite hat. Mit seiner Hilfe kann auch der ungeschickteste Anf�nger beste Noten f�r seine Gerichte bekommen. Der Trick, den der vielfach ausgezeichnete Meisterkoch dabei anwendet, lautet visualisieren. Die einzelnen Arbeitsschritte werden auf Farbfotos im Format von ca. 3x4 cm abgebildet. Unter diesen stehen kurz und knapp die Angaben zur Zubereitung. So pr�sentiert Schuhbecks Kochschule alles bequem auf einen Blick. Und der interessierte Kochneuling kann sich auf die Hauptsache konzentrieren � aufs Kochen. Wie die Speise aussehen soll, zeigt jeweils das Farbfoto auf der linken Seite, auf der auch die Zutaten � dank des geschickten Layouts � ebenfalls sehr �bersichtlich aufgelistet sind. Au�erdem gibt Schuhbeck pr�gnante Empfehlungen zu Zutaten und Zubereitung.'}]";
        assertEquals(expected,books.toString());

    }

    public void testFindAllSortedMagazinesByTitle() throws Exception {
        List<Magazine> magazines = this.editionRepository.findAllSortedMagazinesByTitle();
        assertNotNull(magazines);
        assertEquals(6,magazines.size());
        String expected = "[Magazine{ title='Der Weinkenner'| isbnNUmber = '2547-8548-2541'| authors = pr-walter@optivo.de| releaseDate = 12.12.2002}, Magazine{ title='Gourmet'| isbnNUmber = '2365-8745-7854'| authors = pr-ferdinand@optivo.de| releaseDate = 14.06.2005}, Magazine{ title='Kochen f�r Genie�er'| isbnNUmber = '2365-5632-7854'| authors = pr-lieblich@optivo.de, pr-walter@optivo.de| releaseDate = 01.05.2007}, Magazine{ title='Meine Familie und ich'| isbnNUmber = '4545-8541-2012'| authors = pr-mueller@optivo.de| releaseDate = 10.07.2006}, Magazine{ title='Sch�ner kochen'| isbnNUmber = '5454-5587-3210'| authors = pr-walter@optivo.de| releaseDate = 21.05.2006}, Magazine{ title='Vinum'| isbnNUmber = '1313-4545-8875'| authors = pr-gustafsson@optivo.de| releaseDate = 23.02.2004}]";
        assertEquals(expected,magazines.toString());
    }

    public void testFindAllSortedMagazinesByDate() throws Exception {
        List<Magazine> magazines = this.editionRepository.findAllSortedMagazinesByDate();
        assertNotNull(magazines);
        assertEquals(6,magazines.size());
        String expected = "[Magazine{ title='Kochen f�r Genie�er'| isbnNUmber = '2365-5632-7854'| authors = pr-lieblich@optivo.de, pr-walter@optivo.de| releaseDate = 01.05.2007}, Magazine{ title='Meine Familie und ich'| isbnNUmber = '4545-8541-2012'| authors = pr-mueller@optivo.de| releaseDate = 10.07.2006}, Magazine{ title='Der Weinkenner'| isbnNUmber = '2547-8548-2541'| authors = pr-walter@optivo.de| releaseDate = 12.12.2002}, Magazine{ title='Gourmet'| isbnNUmber = '2365-8745-7854'| authors = pr-ferdinand@optivo.de| releaseDate = 14.06.2005}, Magazine{ title='Sch�ner kochen'| isbnNUmber = '5454-5587-3210'| authors = pr-walter@optivo.de| releaseDate = 21.05.2006}, Magazine{ title='Vinum'| isbnNUmber = '1313-4545-8875'| authors = pr-gustafsson@optivo.de| releaseDate = 23.02.2004}]";
        assertEquals(expected,magazines.toString());
    }

}