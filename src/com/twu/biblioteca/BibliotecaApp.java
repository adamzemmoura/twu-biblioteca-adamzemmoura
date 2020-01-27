package com.twu.biblioteca;

public class BibliotecaApp {

    private Printer printer = Printer.sharedInstance;
    private Menu menu;

    public BibliotecaApp(Menu menu) {
        this.menu = menu;
    }

    public void start() {

        displayWelcomeMessage();

        promptUserToLogIn();

        while(true) {
            menu.display();
            menu.handleUserSelection();
        }
    }

    private void promptUserToLogIn() {

    }

    private void displayWelcomeMessage() {
        printer.printMessage(Strings.WELCOME_MESSAGE);
    }
}
