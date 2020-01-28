package com.twu.biblioteca;

import com.twu.biblioteca.enums.UserRole;
import com.twu.biblioteca.exceptions.AuthenticationException;
import com.twu.biblioteca.factories.MenuFactory;
import com.twu.biblioteca.interfaces.AuthenticationServiceDelegate;
import com.twu.biblioteca.models.User;
import com.twu.biblioteca.resources.Strings;

public class BibliotecaApp implements AuthenticationServiceDelegate {

    private Printer printer = Printer.sharedInstance;
    private Menu menu;
    private AuthenticationService authenticationService;
    private int loginAttempts;

    public BibliotecaApp() {
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

    private void promptUserToLogIn() throws Exception {
        loginAttempts += 1;
        String libraryNumber = ConsoleInputReader.sharedInstance.attemptToReadString(Strings.LOGIN_PROMPT);
        String password = ConsoleInputReader.sharedInstance.attemptToReadString(Strings.PASSWORD_PROMPT);
        authenticationService.attemptLogin(libraryNumber, password);
    }

    private void reattemptLogin() {
        if (loginAttempts < 3) {
            handleUserLogin();
        } else {
            printer.printMessage(Strings.ERROR_MESSAGE_TOO_MANY_LOGIN_ATTEMPTS);
            System.exit(0);
        }

    }

    private void displayWelcomeMessage() {
        printer.printMessage(Strings.WELCOME_MESSAGE);
    }

    @Override
    public void userDidSuccessfullyLogin() {
        User currentUser = authenticationService.getCurrentUser();
        String welcomeMessage = String.format("Logged in as %s. Welcome back %s!",
                currentUser.getRole().toString(),
                currentUser.getName()
        );
        printer.printMessage(welcomeMessage);
        UserRole role = currentUser.getRole();
        this.menu = MenuFactory.createMenuForUserRole(role);
        while(true) {
            menu.display();
            menu.handleUserSelection();
        }
    }
}
