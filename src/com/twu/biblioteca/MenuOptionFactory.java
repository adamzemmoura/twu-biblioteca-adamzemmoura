package com.twu.biblioteca;

import java.util.List;
import java.util.stream.Collectors;

public class MenuOptionFactory {

    public static MenuOption createShowAllBooksMenuOption() {

        Selectable action = () -> {
            List<Book> books = Library.sharedInstance.getAllBooks();
            List<String> booksAsStrings = books.stream().map(Book::toString).collect(Collectors.toList());
            Printer.sharedInstance.printList(booksAsStrings);
        };

        MenuOption option = new MenuOption(Strings.MENU_OPTION_SHOW_ALL_BOOKS, action);

        return option;
    }

}
