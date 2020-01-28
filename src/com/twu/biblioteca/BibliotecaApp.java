package com.twu.biblioteca;

import java.io.IOException;

public class BibliotecaApp implements AuthenticationServiceDelegate {

    private Printer printer = Printer.sharedInstance;
    private Menu menu;
    private AuthenticationService authenticationService;
    private int loginAttempts;

    public BibliotecaApp(Menu menu) {
        this.menu = menu;
        this.authenticationService = AuthenticationService.sharedInstance;
        authenticationService.delegate = this;
        loginAttempts = 0;
    }

    public void start() {
        displayWelcomeMessage();
        handleUserLogin();
    }

    private void handleUserLogin() {
        try {
            promptUserToLogIn();
        } catch (AuthenticationException ae) {
          printer.printMessage(Strings.FAILED_LOGIN_ATTEMPT);
          reattemptLogin();
        } catch (Exception e) {
            printer.printMessage("Something went wrong. Exiting...");
            System.exit(0);
        }
    }

    private void reattemptLogin() {
        if (loginAttempts < 3) {
            handleUserLogin();
        } else {
            printer.printMessage(Strings.ERROR_MESSAGE_TOO_MANY_LOGIN_ATTEMPTS);
            System.exit(0);
        }

    }

    private void promptUserToLogIn() throws Exception {
        loginAttempts += 1;
        String libraryNumber = ConsoleInputReader.sharedInstance.attemptToReadString(Strings.LOGIN_PROMPT);
        String password = ConsoleInputReader.sharedInstance.attemptToReadString(Strings.PASSWORD_PROMPT);
        authenticationService.attemptLogin(libraryNumber, password);
    }

    private void displayWelcomeMessage() {
        printer.printMessage(Strings.WELCOME_MESSAGE);
    }

    @Override
    public void userDidSuccessfullyLogin() {
        User currentUser = authenticationService.getCurrentUser();
        printer.printMessage(Strings.LOGIN_SUCCESS_MESSAGE + currentUser + "!");
        while(true) {
            menu.display();
            menu.handleUserSelection();
        }
    }
}
