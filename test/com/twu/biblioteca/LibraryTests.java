package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

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

}