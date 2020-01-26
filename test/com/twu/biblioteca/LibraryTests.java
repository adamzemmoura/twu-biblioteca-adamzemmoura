package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.List;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class LibraryTests {

    private Library library;

    @Before
    public void setUp() {
        library = Library.sharedInstance;
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

        library.checkOutBookByTitle("Clean Code");
        AvailabilityStatus statusAfterCheckout = library.checkAvailabilityOfBookWithTitle("Clean Code");

        assertThat(statusBeforeCheckout, is(AvailabilityStatus.AVAILABLE));
        assertThat(statusAfterCheckout, is(AvailabilityStatus.UNAVAILABLE));
    }
}