package com.alaeddine.messadi.parsers;

import com.alaeddine.messadi.entities.Author;
import junit.framework.TestCase;

import java.nio.file.Paths;
import java.util.List;

public class BookCSVReaderTest extends TestCase {
    private static final String DATA_DIRACTORY = Paths.get(".").toAbsolutePath().normalize().toString() + "/data/";
    private static final String AUTHOR_CSV = "autoren.csv";
    private static final String MAGAZINE_CSV = "zeitschriften.csv";
    private static final String BOOK_CSV = "buecher.csv";


    private List<Author> authors;

    public void setUp() throws Exception {
        super.setUp();
        this.authors = new AuthorCSVReader(DATA_DIRACTORY+AUTHOR_CSV).getAuthorList();
    }


    public void testGetBookList() throws Exception {

        BookCSVReader bookCSVReader = new BookCSVReader(DATA_DIRACTORY+BOOK_CSV, authors);

        assertNotNull(bookCSVReader.getBookList());
        assertEquals(8, bookCSVReader.getBookList().size());
    }

    public void testToString() throws Exception {

        BookCSVReader bookCSVReader = new BookCSVReader(DATA_DIRACTORY+BOOK_CSV, authors);

        String result = bookCSVReader.toString();
        String expected ="Book{ title='Ich helf dir kochen. Das erfolgreiche Universalkochbuch mit gro�em Backteil'| isbnNUmber = '5554-5545-4518'| authors = pr-walter@optivo.de| summary = 'Auf der Suche nach einem Basiskochbuch steht man heutzutage vor einer F�lle von Alternativen. Es f�llt schwer, daraus die f�r sich passende Mixtur aus Grundlagenwerk und Rezeptesammlung zu finden. Man sollte sich dar�ber im Klaren sein, welchen Schwerpunkt man setzen m�chte oder von welchen Koch- und Backkenntnissen man bereits ausgehen kann.'}\n" +
                "Book{ title='Das gro�e GU-Kochbuch Kochen f�r Kinder'| isbnNUmber = '2145-8548-3325'| authors = pr-ferdinand@optivo.de, pr-lieblich@optivo.de| summary = 'Es beginnt mit... den ersten L�ffelchen f�r Mami, Papi und den Rest der Welt. Ja, und dann? Was Hersteller von Babynahrung k�nnen, das ist oft im Handumdrehen auch selbst gemacht, vielleicht sogar ges�nder, oftmals frischer. �ltere Babys und Schulkinder will das Kochbuch ansprechen und das tut es auf eine verspielte Art angenehm altersgem��.'}\n" +
                "Book{ title='Schlank im Schlaf '| isbnNUmber = '4545-8558-3232'| authors = pr-gustafsson@optivo.de| summary = 'Schlank im Schlaf klingt wie ein sch�ner Traum, aber es ist wirklich m�glich. Allerdings nicht nach einer Salamipizza zum Abendbrot. Die Grundlagen dieses neuartigen Konzepts sind eine typgerechte Insulin-Trennkost sowie Essen und Sport im Takt der biologischen Uhr. Wie die Bio-Uhr tickt und was auf dem Speiseplan stehen sollte, h�ngt vom pers�nlichen Urtyp ab: Nomade oder Ackerbauer?'}\n" +
                "Book{ title='Das Perfekte Dinner. Die besten Rezepte'| isbnNUmber = '2221-5548-8585'| authors = pr-lieblich@optivo.de| summary = 'Sie wollen auch ein perfektes Dinner kreieren? Mit diesem Buch gelingt es Ihnen!'}\n" +
                "Book{ title='Das Piratenkochbuch. Ein Spezialit�tenkochbuch mit den 150 leckersten Rezepten '| isbnNUmber = '3214-5698-7412'| authors = pr-rabe@optivo.de| summary = 'Das Piraten-Kochbuch beweist, dass die Seer�uberkapit�ne zwar gef�rchtete Haudegen waren, andererseits aber manches Mal mit gehobenenem Geschmacksempfinden ausgestattet. ... Kurzum, ein ideales Buch, um maritime Events kulinarisch zu umrahmen.'}\n" +
                "Book{ title='Genial italienisch'| isbnNUmber = '1024-5245-8584'| authors = pr-lieblich@optivo.de, pr-walter@optivo.de, pr-rabe@optivo.de| summary = 'Starkoch Jamie Oliver war mit seinem VW-Bus in Italien unterwegs -- und hat allerlei kulinarische Souvenirs mitgebracht. Es lohnt sich, einen Blick in sein Gep�ck zu werfen...'}\n" +
                "Book{ title='O'Reillys Kochbuch f�r Geeks'| isbnNUmber = '2215-0012-5487'| authors = pr-mueller@optivo.de| summary = 'Nach landl�ufiger Meinung leben Geeks von Cola und TK-Pizza, die sie nachts am Rechner geistesabwesend in sich reinschlingen. Soweit das Klischee! Dass aber Kochen viel mit Programmieren zu tun hat, dass es n�mlich �hnlich kreativ ist, dass viele Wege zum individuellen Ziel f�hren und dass manche Rezepte einfach nur abgefahren und -- ja, geekig sind: Das zeigen zwei K�chinnen in diesem Buch.'}\n" +
                "Book{ title='Schuhbecks Kochschule. Kochen lernen mit Alfons Schuhbeck '| isbnNUmber = '1215-4545-5895'| authors = pr-walter@optivo.de| summary = 'Aller Anfang ist leicht! Zumindest, wenn man beim Kochenlernen einen Lehrer wie Alfons Schuhbeck zur Seite hat. Mit seiner Hilfe kann auch der ungeschickteste Anf�nger beste Noten f�r seine Gerichte bekommen. Der Trick, den der vielfach ausgezeichnete Meisterkoch dabei anwendet, lautet visualisieren. Die einzelnen Arbeitsschritte werden auf Farbfotos im Format von ca. 3x4 cm abgebildet. Unter diesen stehen kurz und knapp die Angaben zur Zubereitung. So pr�sentiert Schuhbecks Kochschule alles bequem auf einen Blick. Und der interessierte Kochneuling kann sich auf die Hauptsache konzentrieren � aufs Kochen. Wie die Speise aussehen soll, zeigt jeweils das Farbfoto auf der linken Seite, auf der auch die Zutaten � dank des geschickten Layouts � ebenfalls sehr �bersichtlich aufgelistet sind. Au�erdem gibt Schuhbeck pr�gnante Empfehlungen zu Zutaten und Zubereitung.'}\n";

        assertEquals(expected, result);

    }

}