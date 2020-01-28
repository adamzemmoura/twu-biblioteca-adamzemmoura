package com.twu.biblioteca.resources;

import com.twu.biblioteca.enums.MovieRating;
import com.twu.biblioteca.enums.UserRole;
import com.twu.biblioteca.interfaces.LibraryResource;
import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.models.Movie;
import com.twu.biblioteca.models.User;

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
            new User("111-1111", UserRole.LIBRARIAN, "Rasputen McTabbernally", "raspy@gmail.com", "602-555-1234"),
            new User("222-2222", UserRole.CUSTOMER, "Donald Duck", "dduck@disney.com", "602-555-1235")
    );
}
