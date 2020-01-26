package com.twu.biblioteca;

import java.util.Arrays;
import java.util.List;

public class BibliotecaApp {

    private Printer printer = Printer.sharedInstance;

    public void start() {
        displayWelcomeMessage();
        displayAllBooks();
    }

    private void displayAllBooks() {
        printer.printMessage(Strings.BOOK_LIST_HEADER);
        List<String> books = Arrays.asList("book1", "book2", "book3");
        printer.printList(books);
    }

    private void displayWelcomeMessage() {
        printer.printMessage(Strings.WELCOME_MESSAGE);
    }
}
