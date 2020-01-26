package com.twu.biblioteca;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BookTests {

    @Test
    public void bookShouldHaveATitle() {
        String title = "title";
        Book book = new Book(title);

        assertThat(book.getTitle(), is(title));
    }

}