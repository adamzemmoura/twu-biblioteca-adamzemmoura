package com.twu.biblioteca;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MenuOptionFactoryTests {

    @Test
    public void canCreateShowAllBooksMenuOption() {
        PrintStream printStreamMock = mock(PrintStream.class);
        Printer printer = Printer.sharedInstance;
        printer.setPrintStream(printStreamMock);

        MenuOption showAllBooks = MenuOptionFactory.createShowAllBooksMenuOption();
        showAllBooks.select();

        verify(printStreamMock).println(generateExpectedBookListOutput());
    }

    private List<Book> testBooks = Arrays.asList(
            new Book("Clean Code", "Robert C. Martin", "1999"),
            new Book("Clean Coder", "Robert C. Martin", "1999"),
            new Book("Refactoring", "Martin Fowler", "1999")
    );

    private String generateExpectedBookListOutput() {
        StringBuilder sb = new StringBuilder();
        String format = "\t%s\n";
        testBooks.stream()
                .map(Book::toString)
                .forEach(book -> sb.append(String.format(format, book)));
        return sb.toString();
    }


}