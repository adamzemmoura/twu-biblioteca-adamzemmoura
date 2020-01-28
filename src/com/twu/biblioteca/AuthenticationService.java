package com.twu.biblioteca;

import com.twu.biblioteca.exceptions.AuthenticationException;
import com.twu.biblioteca.interfaces.AuthenticationServiceDelegate;
import com.twu.biblioteca.models.User;
import com.twu.biblioteca.resources.TestData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthenticationService {

    public static AuthenticationService sharedInstance = new AuthenticationService();
    private static Map<String, String> libraryNumberToPasswordMap;
    public static AuthenticationServiceDelegate delegate = null;
    private static User currentUser;
    private static List<User> users = TestData.users;

    private AuthenticationService() {
        libraryNumberToPasswordMap = new HashMap<>();
        users = TestData.users;
        for (User user : users) {
            String libraryNumber = user.getLibraryNumber();
            String password = "secret";
            libraryNumberToPasswordMap.put(libraryNumber, password);
        }
    }

    public void attemptLogin(String libraryNumber, String password) throws AuthenticationException {
        validateCredentials(libraryNumber, password);
        this.currentUser = lookupUser(libraryNumber);
        if (delegate != null) {
            delegate.userDidSuccessfullyLogin();
        }
    }

    private User lookupUser(String libraryNumber) {
        return users.stream()
                .filter(user -> user.getLibraryNumber().equals(libraryNumber))
                .findFirst()
                .get();
    }

    private void validateCredentials(String libraryNumber, String password) throws AuthenticationException {
        checkLibraryNumberIsValidFormat(libraryNumber);
        checkLibraryNumberIsKnown(libraryNumber);
        checkPasswordIsCorrectForUserWithLibraryNumber(libraryNumber, password);
    }

    private void checkPasswordIsCorrectForUserWithLibraryNumber(String libraryNumber, String password) throws AuthenticationException {
        if (!libraryNumberToPasswordMap.get(libraryNumber).matches(password)) {
            throw new AuthenticationException("Password was not correct");
        }
    }

    private void checkLibraryNumberIsKnown(String libraryNumber) throws AuthenticationException {
        if (!libraryNumberToPasswordMap.containsKey(libraryNumber)) {
            throw new AuthenticationException("Library number is not in system ie. user has not been added");
        }
    }

    private void checkLibraryNumberIsValidFormat(String libraryNumber) throws AuthenticationException {
        if (!libraryNumber.matches("\\d{3}-\\d{4}")) {
            String message = String.format("'%s' is not valid library number format (xxx-xxxx)", libraryNumber);
            throw new AuthenticationException(message);
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void logout() {
        currentUser = null;
    }

}
