package com.twu.biblioteca;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BibliotecaApp {

    private Printer printer = Printer.sharedInstance;
    private Library library = Library.sharedInstance;
    private List<MenuOption> menuOptions;

    public BibliotecaApp() {
        createMenu();
    }

    private void createMenu() {
        menuOptions = Arrays.asList(
                MenuOptionFactory.createShowAllBooksMenuOption()
        );
    }

    public void start() {
        displayWelcomeMessage();
        displayMenu();
    }

    private void displayMenu() {

    }

    private void displayAllBooks() {
        printer.printMessage(Strings.BOOK_LIST_HEADER);
        List<String> printableBooks = library.getAllBooks().stream().map(Book::toString).collect(Collectors.toList());
        printer.printList(printableBooks);
    }

    private void displayWelcomeMessage() {
        printer.printMessage(Strings.WELCOME_MESSAGE);
    }
}
