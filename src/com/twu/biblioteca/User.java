package com.twu.biblioteca;

import java.util.Objects;

public class User {

    private String libraryNumber;
    private UserRole role;

    public User(String libraryNumber, UserRole role) {
        this.role = role;
        this.libraryNumber = libraryNumber;
    }

    public String getLibraryNumber() {
        return libraryNumber;
    }

    public UserRole getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return libraryNumber.equals(user.libraryNumber) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(libraryNumber, role);
    }
}
