package com.twu.biblioteca;

import java.util.List;
import java.util.stream.Collectors;

public class MenuOptionFactory {

    private static Printer printer = Printer.sharedInstance;
    private static ConsoleInputReader inputReader = ConsoleInputReader.sharedInstance;
    private static Library library = Library.sharedInstance;

    public static MenuOption createShowAllBooksMenuOption() {

        Selectable action = () -> {
            printer.printMessage(Strings.BOOK_LIST_HEADER);
            List<LibraryResource> books = Library.sharedInstance.getAvailableBooks();
            List<String> booksAsStrings = books.stream().map(LibraryResource::toString).collect(Collectors.toList());
            printer.printList(booksAsStrings);
        };

        MenuOption option = new MenuOption(Strings.MENU_OPTION_TITLE_SHOW_ALL_BOOKS, action);

        return option;
    }

    public static MenuOption createQuitOption() {
        Selectable action = () -> {
            printer.printMessage(Strings.FAREWELL_MESSAGE);
            System.exit(0);
        };
        return new MenuOption(Strings.MENU_OPTION_TITLE_QUIT, action);
    }

    public static MenuOption createCheckoutBookByTitleOption() {
        Selectable action = () -> {
            try {
                String bookTitle = inputReader.attemptToReadString(Strings.MENU_OPTION_CHECKOUT_BOOK_PROMPT);
                boolean successfullyCheckedOut = library.attemptToCheckOutBookByTitle(bookTitle);
                String userFeedbackMessage = successfullyCheckedOut ?
                        Strings.SUCCESSFUL_BOOK_CHECKOUT_MESSAGE :
                        Strings.UNSUCCESSFUL_BOOK_CHECKOUT_MESSAGE;
                printer.printMessage(userFeedbackMessage);
            } catch (Exception e) {
                printer.printMessage(e.getMessage());
            }
        };
        return new MenuOption(Strings.MENU_OPTION_TITLE_CHECKOUT_BOOK, action);
    }

    public static MenuOption createCheckinBookByTitleOption() {
        Selectable action = () -> {
            try {
                String bookTitle = inputReader.attemptToReadString(Strings.MENU_OPTION_CHECKIN_BOOK_PROMPT);
                boolean succesfullyCheckedIn = library.attemptToCheckinBookByTitle(bookTitle);
                if (succesfullyCheckedIn) printer.printMessage(Strings.SUCCESSFUL_BOOK_CHECKIN_MESSAGE);
            } catch (ItemNotFoundException infe) {
                printer.printMessage(Strings.UNSUCCESSFUL_BOOK_CHECKIN_MESSAGE);
            } catch (Exception e) {
                printer.printMessage(e.getMessage());
            }
        };
        return new MenuOption(Strings.MENU_OPTION_TITLE_CHECKIN_BOOK, action);
    }

    public static MenuOption createShowAllMoviesOption() {
        Selectable action = () -> {
            printer.printMessage(Strings.MOVIE_LIST_HEADER);
            List<String> moviesStringList = library.getAllMovies().stream()
                    .map(LibraryResource::toString)
                    .collect(Collectors.toList());
            printer.printList(moviesStringList);
        };
        return new MenuOption(Strings.MENU_OPTION_TITLE_SHOW_ALL_MOVIES, action);
    }

    public static MenuOption createCheckoutMovieByTitleOption() {

        Selectable action = () -> {
            try {
                String movieTitle = inputReader.attemptToReadString(Strings.MENU_OPTION_TITLE_CHECKOUT_MOVIE_PROMPT);
                boolean successfullyCheckedOut = library.attemptToCheckOutMovieByTitle(movieTitle);
                String userFeedbackMessage = successfullyCheckedOut ?
                        Strings.SUCCESSFUL_MOVIE_CHECKOUT_MESSAGE :
                        Strings.UNSUCCESSFUL_MOVIE_CHECKOUT_MESSAGE;
                printer.printMessage(userFeedbackMessage);
            } catch (Exception e) {
                printer.printMessage(e.getMessage());
            }
        };

        return new MenuOption(Strings.MENU_OPTION_TITLE_CHECKOUT_MOVIES, action);
    }
}
