package com.twu.biblioteca;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Library {

    public static Library sharedInstance = new Library();

    private List<Book> books = Arrays.asList(
            new Book("Clean Code", "Robert C. Martin", "1999"),
            new Book("Clean Coder", "Robert C. Martin", "1999"),
            new Book("Refactoring", "Martin Fowler", "1999")
    );

    private Library() {}

    public List<Book> getAllBooks() {
        return moreThanOneBook() ? books : Collections.EMPTY_LIST;
    }

    private boolean moreThanOneBook() {
        return this.books.size() > 0;
    }
}
