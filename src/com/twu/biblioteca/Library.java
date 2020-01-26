package com.twu.biblioteca;

import java.util.*;

public class Library {

    public static Library sharedInstance = new Library();

    private List<Book> allBooks = Arrays.asList(
            new Book("Clean Code", "Robert C. Martin", "1999"),
            new Book("Clean Coder", "Robert C. Martin", "1999"),
            new Book("Refactoring", "Martin Fowler", "1999")
    );

    private HashMap<UUID, AvailabilityStatus> availabilityStatusHashMap;

    private Library() {
        setAllBooksToAvailable();
    }

    private void setAllBooksToAvailable() {
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
        Book book = tryToGetFirstBookMatchingTitle(title);
        return availabilityStatusHashMap.get(book.getId());
    }



    public void checkOutBookByTitle(String title) throws ItemNotFoundException {
        Book selectedBook = tryToGetFirstBookMatchingTitle(title);
        UUID id = selectedBook.getId();
        availabilityStatusHashMap.put(id, AvailabilityStatus.UNAVAILABLE);
    }

    private Book tryToGetFirstBookMatchingTitle(String title) throws ItemNotFoundException {
        return allBooks.stream()
                    .filter(book -> book.getTitle().equalsIgnoreCase(title))
                    .findFirst()
                    .orElseThrow(
                            () -> new ItemNotFoundException(String.format("Cannot find book with title : '%s'", title)
                    ));
    }
}
