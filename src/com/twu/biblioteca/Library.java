package com.twu.biblioteca;

import java.util.*;
import java.util.stream.Collectors;

public class Library {

    public static Library sharedInstance = new Library();

    private List<Book> allBooks = Arrays.asList(
            new Book("Clean Code", "Robert C. Martin", "1999"),
            new Book("Clean Coder", "Robert C. Martin", "1999"),
            new Book("Refactoring", "Martin Fowler", "1999")
    );

    private List<Movie> allMovies = Arrays.asList(
            new Movie("The Godfather", "1972", "Francis Ford Coppola", MovieRating.FIVE),
            new Movie("The Shawshank Redemption", "1994", "Frank Darabont", MovieRating.THREE),
            new Movie("Schindler's List", "1993", "Steven Spielberg", MovieRating.FIVE),
            new Movie("Raging Bull", "1980", "Martin Scorsese", MovieRating.FOUR),
            new Movie("Casablanca", "1946", "Michael Curtiz", MovieRating.ONE)
    );

    private HashMap<UUID, AvailabilityStatus> availabilityStatusHashMap;

    private Library() {
        setAllBooksToAvailable();
    }

    public void setAllBooksToAvailable() {
        availabilityStatusHashMap = new HashMap<>();
        allBooks.forEach(book -> {
            UUID id = book.getId();
            AvailabilityStatus status = AvailabilityStatus.AVAILABLE;
            availabilityStatusHashMap.put(id, status);
        });
    }

    public List<Book> getAllBooks() {
        return moreThanOneBook() ? allBooks : Collections.EMPTY_LIST;
    }

    private boolean moreThanOneBook() {
        return this.allBooks.size() > 0;
    }

    public AvailabilityStatus checkAvailabilityOfBookWithTitle(String title) throws ItemNotFoundException {
        Book book = attemptToGetFirstBookMatchingTitle(title);
        return availabilityStatusHashMap.get(book.getId());
    }



    public boolean attemptToCheckOutBookByTitle(String title) throws ItemNotFoundException {
        Book selectedBook = attemptToGetFirstBookMatchingTitle(title);
        UUID id = selectedBook.getId();
        if ( bookIsNotAvailable(selectedBook) ) return false;
        availabilityStatusHashMap.put(id, AvailabilityStatus.UNAVAILABLE);
        return true;
    }

    private boolean bookIsNotAvailable(Book book) {
        AvailabilityStatus status = availabilityStatusHashMap.get(book.getId());
        return status == AvailabilityStatus.UNAVAILABLE;
    }

    private boolean bookIsAvailable(Book book) {
        AvailabilityStatus status = availabilityStatusHashMap.get(book.getId());
        return status == AvailabilityStatus.AVAILABLE;
    }

    private Book attemptToGetFirstBookMatchingTitle(String title) throws ItemNotFoundException {
        return allBooks.stream()
                    .filter(book -> book.getTitle().equalsIgnoreCase(title))
                    .findFirst()
                    .orElseThrow(
                            () -> new ItemNotFoundException(String.format("Cannot find book with title : '%s'", title)
                    ));
    }

    public List<Book> getAvailableBooks() {
        return allBooks.stream()
                .filter(this::bookIsAvailable)
                .collect(Collectors.toList());
    }

    public boolean attemptToCheckinBookByTitle(String title) throws ItemNotFoundException {
        Book book = attemptToGetFirstBookMatchingTitle(title);
        availabilityStatusHashMap.put(book.getId(), AvailabilityStatus.AVAILABLE);
        return bookIsAvailable(book);
    }

    public List<Movie> getAllMovies() {
        return allMovies;
    }
}
