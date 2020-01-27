package com.twu.biblioteca;

import com.sun.javaws.exceptions.InvalidArgumentException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MovieTests {

    private Movie testMovie1;
    private String testMovie1Title;
    private String testMovie1YearReleased;
    private String testMovie1Director;
    private MovieRating testMovie1Rating;


    @Before
    public void setUp() {
        testMovie1Title = "title 1";
        testMovie1YearReleased = "2000";
        testMovie1Director = "Bigshot Director";
        testMovie1Rating = MovieRating.THREE;
        testMovie1 = new Movie(testMovie1Title, testMovie1YearReleased, testMovie1Director, testMovie1Rating);
    }

    @Test
    public void moviesMustHaveTitle() {
        assertThat(testMovie1.getTitle(), is(testMovie1Title));
    }

    @Test
    public void moviesMustHaveYear() {
        assertThat(testMovie1.getYearReleased(), is(testMovie1YearReleased));
    }

    @Test
    public void moviesMustHaveDirector() {
        assertThat(testMovie1.getDirector(), is(testMovie1Director));
    }

    @Test
    public void moviesMustHaveRating() {
        assertThat(testMovie1.getRating(), is(testMovie1Rating));
    }

    @Test
    public void movieToStringPrintsNicelyFormattedString() {
        String expectedStarRating = Strings.THREE_STAR_RATING;
        String expectedOutput = String.format("'%s' (%s) %s", testMovie1Title, testMovie1YearReleased, expectedStarRating);
        assertThat(testMovie1.toString(), is(expectedOutput));
    }

}