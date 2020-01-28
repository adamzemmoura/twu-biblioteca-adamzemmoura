package com.twu.biblioteca;

import com.twu.biblioteca.models.Book;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BookTests {

    private static String book1Title = "title1";
    private static String book1Author = "author1";
    private static String book1YearPublished = "2000";
    private Book book1;

    @Before
    public void setUp() {
        book1 = new Book(book1Title, book1Author, book1YearPublished);
    }

    @Test
    public void bookShouldHaveATitle() {
        assertThat(book1.getTitle(), is(book1Title));
    }

    @Test
    public void bookShouldHaveAuthor() {
       assertThat(book1.getAuthor(), is(book1Author));
    }

    @Test
    public void bookShouldShouldHaveYearPublished() {
        assertThat(book1.getYearPublished(), is(book1YearPublished));
    }

    @Test
    public void bookToStringMethodShouldReturnBookInformationWithColumnSeperators() {
        String expectedOutput = String.format("\"%s\" by %s (%s)", book1Title, book1Author, book1YearPublished);
        assertThat(book1.toString(), is(expectedOutput));
    }

}