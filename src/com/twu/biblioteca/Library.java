package com.twu.biblioteca;

import com.twu.biblioteca.enums.AvailabilityStatus;
import com.twu.biblioteca.exceptions.ItemNotFoundException;
import com.twu.biblioteca.interfaces.LibraryResource;
import com.twu.biblioteca.models.User;
import com.twu.biblioteca.resources.TestData;

import java.util.*;
import java.util.stream.Collectors;

public class Library {

    public static Library sharedInstance = new Library();

    private List<LibraryResource> allBooks = TestData.books;

    private List<LibraryResource> allMovies = TestData.movies;

    private HashMap<UUID, AvailabilityStatus> availabilityStatusHashMap;

    private List<LibraryResourceRental> rentals;

    private Library() {
        setInventoryToAvailable();
        this.rentals = new ArrayList<>();
    }

    public void setInventoryToAvailable() {
        availabilityStatusHashMap = new HashMap<>();
        setAllBooksToAvailable();
        setAllMoviesToAvailable();
    }

    private void setAllBooksToAvailable() {
        allBooks.forEach(book -> {
            UUID id = book.getId();
            AvailabilityStatus status = AvailabilityStatus.AVAILABLE;
            availabilityStatusHashMap.put(id, status);
        });
    }

    private void setAllMoviesToAvailable() {
        allMovies.forEach(book -> {
            UUID id = book.getId();
            AvailabilityStatus status = AvailabilityStatus.AVAILABLE;
            availabilityStatusHashMap.put(id, status);
        });
    }

    // Book methods
    public List<LibraryResource> getAllBooks() {
        return moreThanOneBook() ? allBooks : Collections.EMPTY_LIST;
    }

    public List<LibraryResource> getAvailableBooks() {
        return allBooks.stream()
                .filter(this::itemIsAvailable)
                .collect(Collectors.toList());
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

    public boolean attemptToCheckinBookByTitle(String title) throws ItemNotFoundException {
        LibraryResource book = attemptToFilterByTitle(allBooks, title);
        availabilityStatusHashMap.put(book.getId(), AvailabilityStatus.AVAILABLE);
        removeLibraryResourceRentalWithTitle(title);
        return itemIsAvailable(book);
    }

    private void removeLibraryResourceRentalWithTitle(String title) {
        rentals = rentals.stream()
                .filter(resource -> !resource.getResource().getTitle().equals(title) )
                .collect(Collectors.toList());
    }

    // Movie methods
    public List<LibraryResource> getAllMovies() {
        return allMovies;
    }

    public boolean attemptToCheckOutMovieByTitle(String title) throws ItemNotFoundException {
        LibraryResource selectedMovie = attemptToFilterByTitle(allMovies, title);
        return attemptToCheckoutLibraryResource(selectedMovie);
    }

    public AvailabilityStatus checkAvailabilityOfMovieWithTitle(String movieTitle) throws ItemNotFoundException {
        LibraryResource movie = attemptToFilterByTitle(allMovies, movieTitle);
        return availabilityStatusHashMap.get(movie.getId());
    }

    public boolean attemptToCheckinMovieByTitle(String title) throws ItemNotFoundException {
        LibraryResource movie = attemptToFilterByTitle(allMovies, title);
        availabilityStatusHashMap.put(movie.getId(), AvailabilityStatus.AVAILABLE);
        removeLibraryResourceRentalWithTitle(title);
        return itemIsAvailable(movie);
    }
    // LibraryResource methods

    private LibraryResource attemptToFilterByTitle(List<LibraryResource> list, String title) throws ItemNotFoundException {
        return list.stream()
                .filter(item -> item.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElseThrow(
                        () -> new ItemNotFoundException(String.format("Cannot find item with title : '%s'", title)
                        ));
    }

    private boolean attemptToCheckoutLibraryResource(LibraryResource resource) throws ItemNotFoundException {
        User currentUser = AuthenticationService.sharedInstance.getCurrentUser();
        if (currentUser == null) return false;
        if ( itemIsNotAvailable(resource) ) return false;
        availabilityStatusHashMap.put(resource.getId(), AvailabilityStatus.UNAVAILABLE);

        LibraryResourceRental rental = new LibraryResourceRental(currentUser, resource);
        this.rentals.add(rental);

        return true;
    }

    private boolean itemIsNotAvailable(LibraryResource item) {
        AvailabilityStatus status = availabilityStatusHashMap.get(item.getId());
        return status == AvailabilityStatus.UNAVAILABLE;
    }

    private boolean itemIsAvailable(LibraryResource resource) {
        AvailabilityStatus status = availabilityStatusHashMap.get(resource.getId());
        return status == AvailabilityStatus.AVAILABLE;
    }

    public List<LibraryResourceRental> getAllRentals() {
        return this.rentals;
    }

    public List<LibraryResourceRental> getAllRentalsForUser(User user) {
        return rentals.stream()
                .filter(rental -> rental.getUser().equals(user))
                .collect(Collectors.toList());
    }
}
