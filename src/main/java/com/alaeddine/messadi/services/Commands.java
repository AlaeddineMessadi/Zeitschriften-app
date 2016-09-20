package com.alaeddine.messadi.services;

import com.alaeddine.messadi.entities.Book;
import com.alaeddine.messadi.entities.Edition;
import com.alaeddine.messadi.entities.Magazine;
import com.alaeddine.messadi.repositories.EditionRepository;

import java.util.List;
import java.util.Scanner;

public class Commands {

    private static final Scanner console = new Scanner(System.in);

    // Print all Books / magazines with all details
    public static void printAllBooksAndMagazines(EditionRepository editionRepository) {
        System.out.println("************ Print All Books And Magazines *********************\n");

        printEditions(editionRepository.findAllMagazineAndBooks());

        System.out.println("****************************************************************\n");
    }

    // Based on an ISBN number and find out a book / magazine
    public static void printBookOrMagazineByIsbnNumber(EditionRepository editionRepository) {
        System.out.println("********** Find Books or Magazines by ISBN-Number***************\n");
        System.out.print("$ ");
        String isbnNumber = console.nextLine().trim();
        Edition edition = editionRepository.findMagazineOrBookByIsnbNumber(isbnNumber);
        if (edition != null) {
            System.out.println(edition.toString());
        } else {
            System.out.println("\nBook or Magazine not found\n");
        }
        System.out.println("****************************************************************\n");
    }

    // Find and Print all Books / magazines of an author
    public static void printBooksAndMagazinesOfAuthtor(EditionRepository editionRepository) {
        System.out.println("********** List Books and Magazines by Author ******************\n");
        System.out.print("$ ");
        String email = console.nextLine().trim();
        printEditions(editionRepository.findMagazinesAndBooksByAuthor(email));
        System.out.println("****************************************************************\n");
    }

    // Sort All Books by title
    public static void printSortedBooksByTitle(EditionRepository editionRepository) {
        System.out.println("***************** Sorted Books by Title ************************\n");
        printBooks(editionRepository.findAllSortedBooksByTitle());
        System.out.println("****************************************************************\n");
    }

    // Sort All magazines by title
    public static void printSortedMagazinesByTitle(EditionRepository editionRepository) {
        System.out.println("***************** Sorted Magazine by Title **********************\n");
        printMagazine(editionRepository.findAllSortedMagazinesByTitle());
        System.out.println("****************************************************************\n");
    }

    // Sort All magazines by date
    public static void printSortedMagazinesByDate(EditionRepository editionRepository) {
        System.out.println("***************** Sorted Magazine by Date **********************");
        printMagazine(editionRepository.findAllSortedMagazinesByDate());
        System.out.println("****************************************************************");
    }

    // Sort All Books / magazines by title
    public static void printSortedBooksAndMagazinesByTitle(EditionRepository editionRepository) {
        System.out.println("************* Sorted Books and Magazine by Title ***************\n");
        printEditions(editionRepository.findAllSortedMagazineAndBooksByTitle());
        System.out.println("****************************************************************\n");
    }

    private static void printEditions(List<Edition> editions) {
        for (Edition edition : editions) {
            System.out.println(edition.toString());
        }
    }

    private static void printBooks(List<Book> books) {
        for (Book book : books) {
            System.out.println(book.toString());
        }
    }

    private static void printMagazine(List<Magazine> magazines) {
        for (Magazine magazine : magazines) {
            System.out.println(magazine.toString());
        }
    }
}
