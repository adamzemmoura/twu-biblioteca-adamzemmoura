package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.LibraryResource;
import com.twu.biblioteca.models.User;

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

    @Override
    public String toString() {
        String format = "%s rented by %s";
        return String.format(format, resource.toString(), user.toString());
    }
}
