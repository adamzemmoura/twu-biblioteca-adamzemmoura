package com.twu.biblioteca;

import java.util.List;
import java.util.stream.Collectors;

public class MenuOptionFactory {

    private static Printer printer = Printer.sharedInstance;

    public static MenuOption createShowAllBooksMenuOption() {

        Selectable action = () -> {
            printer.printMessage(Strings.BOOK_LIST_HEADER);
            List<Book> books = Library.sharedInstance.getAllBooks();
            List<String> booksAsStrings = books.stream().map(Book::toString).collect(Collectors.toList());
            printer.printList(booksAsStrings);
        };

        MenuOption option = new MenuOption(Strings.MENU_OPTION_SHOW_ALL_BOOKS, action);

        return option;
    }

}
