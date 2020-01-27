package com.twu.biblioteca;

import java.util.*;
import java.util.stream.Collectors;

public class Library {

    public static Library sharedInstance = new Library();

    private List<LibraryResource> allBooks = Arrays.asList(
            new Book("Clean Code", "Robert C. Martin", "1999"),
            new Book("Clean Coder", "Robert C. Martin", "1999"),
            new Book("Refactoring", "Martin Fowler", "1999")
    );

    private List<LibraryResource> allMovies = Arrays.asList(
            new Movie("The Godfather", "1972", "Francis Ford Coppola", MovieRating.FIVE),
            new Movie("The Shawshank Redemption", "1994", "Frank Darabont", MovieRating.THREE),
            new Movie("Schindler's List", "1993", "Steven Spielberg", MovieRating.FIVE),
            new Movie("Raging Bull", "1980", "Martin Scorsese", MovieRating.FOUR),
            new Movie("Casablanca", "1946", "Michael Curtiz", MovieRating.ONE)
    );

    private HashMap<UUID, AvailabilityStatus> availabilityStatusHashMap;

    private Library() {
        setInventoryToAvailable();
    }

    public void setInventoryToAvailable() {
        availabilityStatusHashMap = new HashMap<>();
        setAllBooksToAvailable();
        setAllMoviesToAvailable();
    }

    public void setAllBooksToAvailable() {
        allBooks.forEach(book -> {
            UUID id = book.getId();
            AvailabilityStatus status = AvailabilityStatus.AVAILABLE;
            availabilityStatusHashMap.put(id, status);
        });
    }

    public void setAllMoviesToAvailable() {
        allMovies.forEach(book -> {
            UUID id = book.getId();
            AvailabilityStatus status = AvailabilityStatus.AVAILABLE;
            availabilityStatusHashMap.put(id, status);
        });
    }

    public List<LibraryResource> getAllBooks() {
        return moreThanOneBook() ? allBooks : Collections.EMPTY_LIST;
    }

    private boolean moreThanOneBook() {
        return this.allBooks.size() > 0;
    }

    public AvailabilityStatus checkAvailabilityOfBookWithTitle(String title) throws ItemNotFoundException {
        LibraryResource book = attemptToFilterByTitle(allBooks, title);
        return availabilityStatusHashMap.get(book.getId());
    }

    public boolean attemptToCheckOutBookByTitle(String title) throws ItemNotFoundException {
        // check that the book is known to the library
        LibraryResource selectedBook = attemptToFilterByTitle(allBooks, title);
        return attemptToCheckoutLibraryResource(selectedBook);
    }

    public boolean attemptToCheckOutMovieByTitle(String title) throws ItemNotFoundException {
        LibraryResource selectedMovie = attemptToFilterByTitle(allMovies, title);
        return attemptToCheckoutLibraryResource(selectedMovie);
    }

    private boolean attemptToCheckoutLibraryResource(LibraryResource resource) throws ItemNotFoundException {
        UUID id = resource.getId();
        if ( itemIsNotAvailable(resource) ) return false;
        availabilityStatusHashMap.put(id, AvailabilityStatus.UNAVAILABLE);
        return true;
    }


    private LibraryResource attemptToFilterByTitle(List<LibraryResource> list, String title) throws ItemNotFoundException {
        return list.stream()
                .filter(item -> item.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElseThrow(
                        () -> new ItemNotFoundException(String.format("Cannot find item with title : '%s'", title)
                        ));
    }

    public AvailabilityStatus checkAvailabilityOfMovieWithTitle(String movieTitle) throws ItemNotFoundException {
        LibraryResource movie = attemptToFilterByTitle(allMovies, movieTitle);
        return availabilityStatusHashMap.get(movie.getId());
    }

    private boolean itemIsNotAvailable(LibraryResource item) {
        AvailabilityStatus status = availabilityStatusHashMap.get(item.getId());
        return status == AvailabilityStatus.UNAVAILABLE;
    }

    private boolean itemIsAvailable(LibraryResource resource) {
        AvailabilityStatus status = availabilityStatusHashMap.get(resource.getId());
        return status == AvailabilityStatus.AVAILABLE;
    }

    public List<LibraryResource> getAvailableBooks() {
        return allBooks.stream()
                .filter(this::itemIsAvailable)
                .collect(Collectors.toList());
    }

    public boolean attemptToCheckinBookByTitle(String title) throws ItemNotFoundException {
        LibraryResource book = attemptToFilterByTitle(allBooks, title);
        availabilityStatusHashMap.put(book.getId(), AvailabilityStatus.AVAILABLE);
        return itemIsAvailable(book);
    }

    public List<LibraryResource> getAllMovies() {
        return allMovies;
    }
}
