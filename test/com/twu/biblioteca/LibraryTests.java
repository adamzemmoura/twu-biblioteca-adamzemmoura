package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;
import java.util.List;
import java.util.stream.Collectors;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LibraryTests {

    private Library library;

    @Before
    public void setUp() {
        library = Library.sharedInstance;
    }

    @After
    public void tearDown() {
        library.setAllBooksToAvailable();
    }

    @Test
    public void libraryIsASingleton() {
        assertNotNull(library);
    }

    @Test
    public void libraryReturnsListOfBooksWhenThereAreBooks() {
        List<Book> books = library.getAllBooks();
        assertTrue("There is one or more books", books.size() > 0);
    }

    @Test
    public void canCheckOutBookThatIsAvailable() throws Exception {
        AvailabilityStatus statusBeforeCheckout = library.checkAvailabilityOfBookWithTitle("Clean Code");

        library.attemptToCheckOutBookByTitle("Clean Code");
        AvailabilityStatus statusAfterCheckout = library.checkAvailabilityOfBookWithTitle("Clean Code");

        assertThat(statusBeforeCheckout, is(AvailabilityStatus.AVAILABLE));
        assertThat(statusAfterCheckout, is(AvailabilityStatus.UNAVAILABLE));
    }

    @Test
    public void cannotCheckoutBookThatIsUnavailable() throws Exception {
        boolean firstCheckoutAttempt = library.attemptToCheckOutBookByTitle("Clean Code");
        boolean secondCheckoutAttempt = library.attemptToCheckOutBookByTitle("Clean Code");

        assertThat(firstCheckoutAttempt, is(true));
        assertThat(secondCheckoutAttempt, is(false));
    }

    @Test (expected = ItemNotFoundException.class)
    public void attemptingToCheckOutBookWithUnknownTitleThrows() throws Exception {
        library.attemptToCheckOutBookByTitle("this book does not exist");
    }

    @Test
    public void attemptingToCheckoutBookWithCorrectTitleIgnoringCaseSucceeds() throws Exception {
        boolean result = library.attemptToCheckOutBookByTitle("cLeaN CodE");
        assertThat(result, is(true));
    }

    @Test
    public void checkedOutBooksNotIncludedInResultsFromCallToGetAllAvailableBooks() throws Exception {
        boolean _ = library.attemptToCheckOutBookByTitle("Clean Code");

        boolean checkedOutBookIncluded = library.getAvailableBooks().stream()
                .filter(book -> book.getTitle().contains("Clean Code"))
                .collect(Collectors.toList())
                .isEmpty();

        assertThat(checkedOutBookIncluded, is(false));
    }
}