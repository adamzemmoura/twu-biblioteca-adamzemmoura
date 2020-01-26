package com.twu.biblioteca;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BibliotecaApp {

    private Printer printer = Printer.sharedInstance;
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
        printer.printMessage(Strings.MENU_HEADER);
        List<String> menuOptionTitles = getMenuOptionTitles();
        printer.printNumberList(menuOptionTitles);
    }

    private List<String> getMenuOptionTitles() {
        return menuOptions.stream()
                .map(menuOption -> menuOption.getTitle() )
                .collect(Collectors.toList());
    }

    private void displayWelcomeMessage() {
        printer.printMessage(Strings.WELCOME_MESSAGE);
    }
}
