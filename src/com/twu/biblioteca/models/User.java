package com.twu.biblioteca.models;

import com.twu.biblioteca.enums.UserRole;

public class User {

    private String libraryNumber;
    private UserRole role;
    private String name;
    private String email;
    private String phoneNumber;

    public User(String libraryNumber, UserRole role, String name, String email, String phoneNumber) {
        this.libraryNumber = libraryNumber;
        this.role = role;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getLibraryNumber() {
        return libraryNumber;
    }

    public UserRole getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        String format = "%s, email : %s, phone number : %s";
        return String.format(format, name, email, phoneNumber);
    }
}
