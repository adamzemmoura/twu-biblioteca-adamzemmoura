package com.twu.biblioteca;

import com.twu.biblioteca.exceptions.AuthenticationException;
import com.twu.biblioteca.interfaces.AuthenticationServiceDelegate;
import com.twu.biblioteca.models.User;
import com.twu.biblioteca.resources.TestData;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AuthenticationServiceTests {

    private AuthenticationService authenticationService;
    private User testUser;
    private String libraryNumber;
    private AuthenticationServiceDelegate delegateMock;

    @Before
    public void setUp() throws Exception {
        authenticationService = AuthenticationService.sharedInstance;
        testUser = TestData.users.get(0);
        libraryNumber = testUser.getLibraryNumber();
        delegateMock = mock(AuthenticationServiceDelegate.class);
        authenticationService.delegate = delegateMock;
    }

    @Test
    public void attemptingToLoginWithCorrectLibraryNumberAndPasswordSucceeds() throws Exception {
        String password = "secret";

        authenticationService.attemptLogin(libraryNumber, password);
        User currentlyLoggedInUser = authenticationService.getCurrentUser();

        assertThat(authenticationService.getCurrentUser(), is(currentlyLoggedInUser));
        verify(delegateMock).userDidSuccessfullyLogin();
    }

    @Test (expected = AuthenticationException.class)
    public void attemptingToLoginWithCorrectLibraryNumberButWrongPasswordThrows() throws AuthenticationException {
        authenticationService.attemptLogin(libraryNumber, "wrongpassword");
    }

    @Test (expected = AuthenticationException.class)
    public void attemptingToLoginWithUnknownLibraryNumberThrows() throws AuthenticationException {
        authenticationService.attemptLogin("123-1234", "secret");
    }

    @Test (expected = AuthenticationException.class)
    public void attemptingToLoginWithBadlyFormattedLibraryNumberThrows() throws AuthenticationException {
        authenticationService.attemptLogin("1111-111", "secret");
    }

    @Test
    public void delegateReceivesUserDidSuccessfullyLogin() throws AuthenticationException {
        System.out.println(libraryNumber);
        authenticationService.attemptLogin(libraryNumber, "secret");
        verify(delegateMock).userDidSuccessfullyLogin();
    }

    @Test
    public void logoutSetsCurrentlyLoggedInUserToNull() throws Exception {
        User testUser = TestData.users.get(0);
        authenticationService.attemptLogin(testUser.getLibraryNumber(), "secret");
        User currentUser = authenticationService.getCurrentUser();

        authenticationService.logout();

        assertNotNull(currentUser);
        assertNull(authenticationService.getCurrentUser());
    }

}