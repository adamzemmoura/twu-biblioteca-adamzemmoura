package com.twu.biblioteca;

import javafx.scene.control.TextField;

import java.util.Arrays;
import java.util.List;

public class TestData {

    public static final List<LibraryResource> books = Arrays.asList(
            new Book("Clean Code", "Robert C. Martin", "1999"),
            new Book("Clean Coder", "Robert C. Martin", "1999"),
            new Book("Refactoring", "Martin Fowler", "1999")
    );


    public static final List<LibraryResource> movies = Arrays.asList(
            new Movie("The Godfather", "1972", "Francis Ford Coppola", MovieRating.FIVE),
            new Movie("The Shawshank Redemption", "1994", "Frank Darabont", MovieRating.THREE),
            new Movie("Schindler's List", "1993", "Steven Spielberg", MovieRating.FIVE),
            new Movie("Raging Bull", "1980", "Martin Scorsese", MovieRating.FOUR),
            new Movie("Casablanca", "1946", "Michael Curtiz", MovieRating.ONE)
    );


    public static final List<User> users = Arrays.asList(
            new User("111-1111", UserRole.LIBRARIAN),
            new User("222-3333", UserRole.CUSTOMER)
    );
}
