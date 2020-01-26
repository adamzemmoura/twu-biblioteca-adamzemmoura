package com.twu.biblioteca;

public class BibliotecaApp {

    public void start() {
        displayWelcomeMessage();
    }

    private void displayWelcomeMessage() {
        Printer.sharedInstance.printMessage(Strings.WELCOME_MESSAGE);
    }
}
