package com.twu.biblioteca;

import java.util.Collections;
import java.util.List;

public class Library {

    public static Library sharedInstance = new Library();

    private Library() {}

    public List<Book> getAllBooks() {
        return Collections.EMPTY_LIST;
    }
}
