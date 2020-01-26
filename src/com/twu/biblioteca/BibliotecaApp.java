package com.twu.biblioteca;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BibliotecaApp {

    private Printer printer = Printer.sharedInstance;

    public void start() {
        displayWelcomeMessage();
        displayAllBooks();
    }

    private void displayAllBooks() {
        printer.printMessage(Strings.BOOK_LIST_HEADER);
        List<Book> books = Arrays.asList(
                new Book("Clean Code", "Robert C. Martin", "1999"),
                new Book("Clean Coder", "Robert C. Martin", "1999"),
                new Book("Refactoring", "Martin Fowler", "1999")

        );
        List<String> printableBooks = books.stream().map(Book::toString).collect(Collectors.toList());
        printer.printList(printableBooks);
    }

    private void displayWelcomeMessage() {
        printer.printMessage(Strings.WELCOME_MESSAGE);
    }
}
