package com.alaeddine.messadi;

import com.alaeddine.messadi.container.CSVReader;
import com.alaeddine.messadi.repositories.EditionRepository;
import com.alaeddine.messadi.services.Commands;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    private static final String availableCmd = "01234";
    private static final String AUTHOR_CSV = "autoren.csv";
    private static final String MAGAZINE_CSV = "zeitschriften.csv";
    private static final String BOOK_CSV = "buecher.csv";

    private static CSVReader csvContainer;

    static {
        csvContainer = new CSVReader(AUTHOR_CSV, MAGAZINE_CSV, BOOK_CSV);
    }

    public static void main(String[] args)  throws InterruptedException {

        Scanner console = new Scanner(System.in);

        while (true) {
            printMenu();
            System.out.print("$ ");
            String userInput = console.nextLine().trim();
            if (!userInput.equals("")) {
                char cmd = userInput.toLowerCase().charAt(0);
                if (availableCmd.indexOf(cmd) > -1) {
                    EditionRepository editionRepository = new EditionRepository(csvContainer);
                    switch (cmd) {
                        case '0':
                            Commands.printAllBooksAndMagazines(editionRepository);
                            break;
                        case '1':
                            Commands.printBookOrMagazineByIsbnNumber(editionRepository);
                            break;
                        case '2':
                            Commands.printBooksAndMagazinesOfAuthtor(editionRepository);
                            break;
                        case '3':
                            Commands.printSortedBooksByTitle(editionRepository);
                            Commands.printSortedMagazinesByTitle(editionRepository);
                            Commands.printSortedMagazinesByDate(editionRepository);
                            Commands.printSortedBooksAndMagazinesByTitle(editionRepository);
                            break;
                        case '4':
                            System.out.println("Exit...");
                            System.exit(0);

                    }
                }
            }
            Thread.sleep(3000);
        }
    }

    private static void printMenu() {
        System.out.println("***************************************************************");
        System.out.println("*********************  Zeitschriften-app **********************");
        System.out.println("***************************************************************");
        System.out.println("*                                                             *");
        System.out.println("*                             CMD                             *");
        System.out.println("*                                                             *");
        System.out.println("*   0) Print all Books / magazines with all details           *");
        System.out.println("*   1) Based on an ISBN number and find out a book / magazine *");
        System.out.println("*   2) Find and Print all Books / magazines of an author      *");
        System.out.println("*   3) Sort All Books / magazines by title and spend          *");
        System.out.println("*                                                             *");
        System.out.println("***************************************************************");
        System.out.println("*   4)                     to Exit                            *");
        System.out.println("***************************************************************");
    }

}