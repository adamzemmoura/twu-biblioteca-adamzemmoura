package com.twu.biblioteca;

import com.twu.biblioteca.enums.AvailabilityStatus;
import com.twu.biblioteca.exceptions.AuthenticationException;
import com.twu.biblioteca.exceptions.ItemNotFoundException;
import com.twu.biblioteca.interfaces.LibraryResource;
import com.twu.biblioteca.models.User;
import com.twu.biblioteca.resources.TestData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class LibraryTests {

    private Library library;
    private AuthenticationService authenticationService;
    private User testUser;

    @Before
    public void setUp() {
        library = Library.sharedInstance;
        authenticationService = AuthenticationService.sharedInstance;
        testUser = TestData.users.get(0);
        loginTestUserToAuthService();
    }

    @After
    public void tearDown() {
        library.setInventoryToAvailable();
        authenticationService.logout();
    }

    private void loginTestUserToAuthService() {
        try {
            authenticationService.attemptLogin(testUser.getLibraryNumber(), "secret");
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void libraryIsASingleton() {
        assertNotNull(library);
    }

    @Test
    public void libraryReturnsListOfBooksWhenThereAreBooks() {
        List<LibraryResource> books = library.getAllBooks();
        assertTrue("There is one or more books", books.size() > 0);
    }

    @Test
    public void canCheckOutBookThatIsAvailable() throws Exception {
        AvailabilityStatus statusBeforeCheckout = library.checkAvailabilityOfBookWithTitle("Clean Code");

        library.attemptToCheckOutBookByTitle("Clean Code");
        AvailabilityStatus statusAfterCheckout = library.checkAvailabilityOfBookWithTitle("Clean Code");

        assertThat(statusBeforeCheckout, is(AvailabilityStatus.AVAILABLE));
        assertThat(statusAfterCheckout, is(AvailabilityStatus.UNAVAILABLE));

        library.attemptToCheckinBookByTitle("Clean Code");
    }

    @Test
    public void cannotCheckoutBookThatIsUnavailable() throws Exception {
        boolean firstCheckoutAttempt = library.attemptToCheckOutBookByTitle("Clean Code");
        boolean secondCheckoutAttempt = library.attemptToCheckOutBookByTitle("Clean Code");

        assertThat(firstCheckoutAttempt, is(true));
        assertThat(secondCheckoutAttempt, is(false));
    }

    @Test
    public void canCheckABookBackInIfTheBookBelongsToTheLibrary() throws Exception {
        String title = "Clean Code";
        boolean checkoutAttempt = library.attemptToCheckOutBookByTitle(title);

        boolean checkinAttempt = library.attemptToCheckinBookByTitle(title);

        assertThat(checkoutAttempt, is(true));
        assertThat(checkinAttempt, is(true));
    }

    @Test (expected = ItemNotFoundException.class)
    public void cannotCheckABookBackInIfItDoesNotBelongsToTheLibrary() throws Exception {
        String title = "This book is not in the library";

        library.attemptToCheckinBookByTitle(title);
    }

    @Test (expected = ItemNotFoundException.class)
    public void attemptingToCheckOutBookWithUnknownTitleThrows() throws Exception {
        library.attemptToCheckOutBookByTitle("this book does not exist");
    }

    @Test
    public void attemptingToCheckoutBookWithCorrectTitleIgnoringCaseSucceeds() throws Exception {
        boolean result = library.attemptToCheckOutBookByTitle("cLeaN CodE");
        assertThat(result, is(true));
        library.attemptToCheckinBookByTitle("Clean Code");
    }

    @Test
    public void checkedOutBooksNotIncludedInResultsFromCallToGetAllAvailableBooks() throws Exception {
        boolean _ = library.attemptToCheckOutBookByTitle("Clean Code");

        boolean checkedOutBookIncluded = library.getAvailableBooks().stream()
                .filter(book -> book.getTitle().contains("Clean Code"))
                .collect(Collectors.toList())
                .isEmpty();

        assertThat(checkedOutBookIncluded, is(false));

        library.attemptToCheckinBookByTitle("Clean Code");
    }

    @Test
    public void canReturnAListOfAllMovies() {
        List<LibraryResource> movies = library.getAllMovies();
        assertThat(movies.size(), is(not(0)));
    }

    @Test
    public void canCheckOutMovieThatIsAvailable() throws Exception {
        String movieTitle = "The Godfather";
        AvailabilityStatus statusBeforeCheckout = library.checkAvailabilityOfMovieWithTitle(movieTitle);

        library.attemptToCheckOutMovieByTitle(movieTitle);
        AvailabilityStatus statusAfterCheckout = library.checkAvailabilityOfMovieWithTitle(movieTitle);

        assertThat(statusBeforeCheckout, is(AvailabilityStatus.AVAILABLE));
        assertThat(statusAfterCheckout, is(AvailabilityStatus.UNAVAILABLE));

        library.attemptToCheckinMovieByTitle(movieTitle);
    }

    @Test
    public void checkingOutALibraryResourceShouldFailIfNoUserLoggedIntoAuthenticationService() throws Exception {
        authenticationService.logout();
        LibraryResource book = TestData.books.get(0);
        LibraryResource movie = TestData.movies.get(0);

        boolean checkedOutBook = library.attemptToCheckOutBookByTitle(book.getTitle());
        boolean checkedOutMovie = library.attemptToCheckOutMovieByTitle(movie.getTitle());

        library.attemptToCheckinBookByTitle(book.getTitle());
        library.attemptToCheckinMovieByTitle(movie.getTitle());

        assertThat(checkedOutBook, is(false));
        assertThat(checkedOutMovie, is(false));
    }

    @Test
    public void whenAUserChecksOutALibraryResourcesLibraryResourceRentalAddedToLibrary() throws Exception {
        LibraryResource movie = TestData.movies.get(0);
        LibraryResource book = TestData.books.get(0);
        library.attemptToCheckOutMovieByTitle(movie.getTitle());
        library.attemptToCheckOutBookByTitle(book.getTitle());

        List<LibraryResourceRental> rentals = library.getAllRentals();

        assertThat(rentals.size(), is(2));

        library.attemptToCheckinMovieByTitle(movie.getTitle());
        library.attemptToCheckinBookByTitle(book.getTitle());
    }

    @Test
    public void whenAUserChecksALibraryResourceBackInTheCorrespondingLibraryResourceRentalIsRemoved() throws Exception {
        LibraryResource book = TestData.books.get(0);
        library.attemptToCheckOutBookByTitle(book.getTitle());
        List<LibraryResourceRental> rentalsAfterCheckout = library.getAllRentals();

        library.attemptToCheckinBookByTitle(book.getTitle());
        List<LibraryResourceRental> rentalsAfterCheckin = library.getAllRentals();

        assertThat(rentalsAfterCheckout.size(), is(1));
        assertThat(rentalsAfterCheckin.size(), is(0));
    }

    @Test
    public void canRetrieveAListOfRentalsForGivenUser() throws Exception {
        LibraryResource book = TestData.books.get(0);
        LibraryResource movie = TestData.movies.get(0);
        library.attemptToCheckOutBookByTitle(book.getTitle());
        library.attemptToCheckOutMovieByTitle(movie.getTitle());

        List<LibraryResourceRental> rentals = library.getAllRentalsForUser(testUser);
        List<LibraryResource> resources = rentals.stream().map(resource -> resource.getResource()).collect(Collectors.toList());

        library.attemptToCheckinBookByTitle(book.getTitle());
        library.attemptToCheckinMovieByTitle(movie.getTitle());

        assertThat(rentals.size(), is(2));
        assertTrue(resources.contains(book));
        assertTrue(resources.contains(movie));
    }
}