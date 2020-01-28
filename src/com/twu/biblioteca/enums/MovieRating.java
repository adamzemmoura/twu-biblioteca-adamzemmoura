package com.twu.biblioteca.enums;

import com.twu.biblioteca.resources.Strings;

public enum MovieRating {
    ONE, TWO, THREE, FOUR, FIVE;

    public String toString() {
       switch (this) {
           case FIVE: return Strings.FIVE_STAR_RATING;
           case FOUR: return Strings.FOUR_STAR_RATING;
           case THREE: return Strings.THREE_STAR_RATING;
           case TWO: return Strings.TWO_STAR_RATING;
           case ONE: return Strings.ONE_STAR_RATING;
           default: return "";
       }
    }
}
