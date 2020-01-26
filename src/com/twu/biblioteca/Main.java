package com.twu.biblioteca;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Menu menu = createMenu();
        BibliotecaApp app = new BibliotecaApp(menu);
        app.start();
    }

    private static Menu createMenu() {
        List<MenuOption> menuOptions = Arrays.asList(
                MenuOptionFactory.createShowAllBooksMenuOption(),
                MenuOptionFactory.createCheckoutBookByTitleOption(),
                MenuOptionFactory.createQuitOption()
        );
        return new Menu(menuOptions);
    }
}
