package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

public class MenuOptionFactoryTests {

    private PrintStream printStreamMock;
    private Printer printer;
    private BufferedReader bufferedReader;
    private Library library;
    private ConsoleInputReader inputReader;

    @Before
    public void setUp() {
        printStreamMock = mock(PrintStream.class);
        bufferedReader = mock(BufferedReader.class);
        printer = Printer.sharedInstance;
        printer.setPrintStream(printStreamMock);
        inputReader = ConsoleInputReader.sharedInstance;
        inputReader.setBufferedReader(bufferedReader);
        library = Library.sharedInstance;
    }

    @After
    public void tearDown() {
        library.setInventoryToAvailable();
    }

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void canCreateShowAllBooksMenuOption() {
        MenuOption showAllBooks = MenuOptionFactory.createShowAllBooksMenuOption();
        showAllBooks.select();

        verify(printStreamMock).println(generateExpectedBookListOutput());
    }

    @Test
    public void canCreateQuitOption() {
        MenuOption quitOption = MenuOptionFactory.createQuitOption();
        exit.expectSystemExit();
        quitOption.select();
    }

    @Test
    public void canCreateCheckoutBookByTitleOption() throws Exception {
        MenuOption checkoutBookByTitleOption = MenuOptionFactory.createCheckoutBookByTitleOption();
        when(bufferedReader.readLine()).thenReturn("Clean Code");

        AvailabilityStatus statusBeforeOptionSelected = library.checkAvailabilityOfBookWithTitle("Clean Code");
        checkoutBookByTitleOption.select();
        AvailabilityStatus statusAfterOptionSelected = library.checkAvailabilityOfBookWithTitle("Clean Code");

        assertThat(statusBeforeOptionSelected, is(AvailabilityStatus.AVAILABLE));
        assertThat(statusAfterOptionSelected, is(AvailabilityStatus.UNAVAILABLE));
    }

    @Test
    public void successMessageDisplayedOnSuccessfulCheckoutOfBook() throws Exception {
        MenuOption checkoutBookByTitleOption = MenuOptionFactory.createCheckoutBookByTitleOption();
        when(bufferedReader.readLine()).thenReturn("Clean Code");
        printer.setPrintStream(printStreamMock);

        checkoutBookByTitleOption.select();

        verify(printStreamMock).println(Strings.SUCCESSFUL_BOOK_CHECKOUT_MESSAGE);
    }

    @Test
    public void messageDisplayedOnUnsuccessfulCheckoutOfBook() throws Exception {
        String bookTitle = "Clean Code";
        MenuOption checkoutBookByTitleOption = MenuOptionFactory.createCheckoutBookByTitleOption();
        boolean successfullyCheckedOut = library.attemptToCheckOutBookByTitle(bookTitle);
        when(bufferedReader.readLine()).thenReturn(bookTitle);
        printer.setPrintStream(printStreamMock);

        checkoutBookByTitleOption.select();

        assertThat(successfullyCheckedOut, is(true));
        verify(printStreamMock).println(Strings.UNSUCCESSFUL_BOOK_CHECKOUT_MESSAGE);
    }

    @Test
    public void canCreateCheckinBookByTitleOption() throws Exception {
        String title = "Clean Code";
        MenuOption checkinBookByTitleOption = MenuOptionFactory.createCheckinBookByTitleOption();
        when(bufferedReader.readLine()).thenReturn(title);
        boolean successfullyCheckedOut = library.attemptToCheckOutBookByTitle(title);
        AvailabilityStatus statusBeforeOptionSelected = library.checkAvailabilityOfBookWithTitle(title);

        checkinBookByTitleOption.select();
        AvailabilityStatus statusAfterOptionSelected = library.checkAvailabilityOfBookWithTitle(title);

        assertThat(successfullyCheckedOut, is(true));
        assertThat(statusBeforeOptionSelected, is(AvailabilityStatus.UNAVAILABLE));
        assertThat(statusAfterOptionSelected, is(AvailabilityStatus.AVAILABLE));
    }

    @Test
    public void messageDisplayedOnSuccessfulCheckinOfBook() throws Exception {
        String bookTitle = "Clean Code";
        MenuOption checkinBookByTitleOption = MenuOptionFactory.createCheckinBookByTitleOption();
        boolean successfullyCheckedOut = library.attemptToCheckOutBookByTitle(bookTitle);
        when(bufferedReader.readLine()).thenReturn(bookTitle);
        printer.setPrintStream(printStreamMock);

        checkinBookByTitleOption.select();

        assertThat(successfullyCheckedOut, is(true));
        verify(printStreamMock).println(Strings.SUCCESSFUL_BOOK_CHECKIN_MESSAGE);
    }

    @Test
    public void messageDisplayedOnUnsuccessfulCheckinOfBook() throws Exception {
        MenuOption checkinBookByTitleOption = MenuOptionFactory.createCheckinBookByTitleOption();
        String bookTitle = "This book is not in the library";
        when(bufferedReader.readLine()).thenReturn(bookTitle);
        printer.setPrintStream(printStreamMock);

        checkinBookByTitleOption.select();

        verify(printStreamMock).println(Strings.UNSUCCESSFUL_BOOK_CHECKIN_MESSAGE);
    }

    @Test
    public void canCreateShowAllMoviesOption() {
        MenuOption listAllMoviesOption = MenuOptionFactory.createShowAllMoviesOption();
        String moviesAsList = library.getAllMovies().stream()
                .map(LibraryResource::toString)
                .collect(Collectors.joining("\n"));

        listAllMoviesOption.select();

        verify(printStreamMock).println(generateExpectedMovieListOutput());
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

    private String generateExpectedMovieListOutput() {
        StringBuilder sb = new StringBuilder();
        String format = "\t%s\n";
        library.getAllMovies().stream()
                .map(LibraryResource::toString)
                .forEach(movie -> sb.append(String.format(format, movie)));
        return sb.toString();
    }


}