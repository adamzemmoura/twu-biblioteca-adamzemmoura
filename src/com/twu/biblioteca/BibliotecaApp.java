package com.twu.biblioteca;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BibliotecaApp {

    private Printer printer = Printer.sharedInstance;
    private Menu menu;

    public BibliotecaApp(Menu menu) {
        this.menu = menu;
    }

    public void start() {

        displayWelcomeMessage();

        while(true) {
            menu.display();
            menu.handleUserSelection();
        }
    }









    private void displayWelcomeMessage() {
        printer.printMessage(Strings.WELCOME_MESSAGE);
    }
}
