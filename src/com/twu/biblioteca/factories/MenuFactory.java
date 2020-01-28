package com.twu.biblioteca.factories;

import com.twu.biblioteca.Menu;
import com.twu.biblioteca.MenuOption;
import com.twu.biblioteca.enums.UserRole;

import java.util.Arrays;
import java.util.List;

public class MenuFactory {

    public static Menu createMenuForUserRole(UserRole role) {
        switch (role) {
            case LIBRARIAN: return createLibrarianMenu();
            default: return createStandardMenu();
        }
    }

    private static Menu createLibrarianMenu() {
        List<MenuOption> options = Arrays.asList(
                MenuOptionFactory.createShowAllBooksMenuOption(),
                MenuOptionFactory.createCheckoutBookByTitleOption(),
                MenuOptionFactory.createShowAllMoviesOption(),
                MenuOptionFactory.createShowCurrentRentalsOption(),
                MenuOptionFactory.createQuitOption()
        );
        return new Menu(options);
    }

    private static Menu createStandardMenu() {
        List<MenuOption> menuOptions = Arrays.asList(
                MenuOptionFactory.createShowAllBooksMenuOption(),
                MenuOptionFactory.createCheckoutBookByTitleOption(),
                MenuOptionFactory.createCheckinBookByTitleOption(),
                MenuOptionFactory.createShowAllMoviesOption(),
                MenuOptionFactory.createCheckoutMovieByTitleOption(),
                MenuOptionFactory.createQuitOption()
        );
        return new Menu(menuOptions);
    }
}
