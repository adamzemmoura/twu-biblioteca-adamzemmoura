package com.twu.biblioteca.models;

import com.twu.biblioteca.interfaces.LibraryResource;

import java.util.UUID;

public class Book implements LibraryResource {

    private final String author;
    private final String title;
    private final String yearPublished;
    private final UUID id = UUID.randomUUID();

    public Book(String title, String author, String yearPublished) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getYearPublished() {
        return yearPublished;
    }

    @Override
    public String toString() {
        String format = "\"%s\" by %s (%s)";
        return String.format(format, title, author, yearPublished);
    }

    public UUID getId() {
        return this.id;
    }
}
