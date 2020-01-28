package com.twu.biblioteca;

public class LibraryResourceRental {
    private User user;
    private LibraryResource resource;

    public LibraryResourceRental(User user, LibraryResource resource) {
        this.user = user;
        this.resource = resource;
    }

    public User getUser() {
        return user;
    }

    public LibraryResource getResource() {
        return resource;
    }
}
