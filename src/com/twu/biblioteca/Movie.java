package com.twu.biblioteca;

import java.util.UUID;

public class Movie implements LibraryResource {

    private final UUID id = UUID.randomUUID();
    private final String title;
    private final String yearReleased;
    private final String director;
    private MovieRating rating;

    public Movie(String title, String yearReleased, String director, MovieRating rating) {
        this.title = title;
        this.yearReleased = yearReleased;
        this.director = director;
        this.rating = rating;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getYearReleased() {
        return this.yearReleased;
    }

    public String getDirector() {
        return this.director;
    }

    public MovieRating getRating() {
        return this.rating;
    }

    @Override
    public String toString() {
        return String.format("'%s' (%s) %s", title, yearReleased, rating.toString());
    }
}
