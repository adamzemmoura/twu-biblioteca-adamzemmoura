package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class PrinterTests {

    private Printer printer;
    private PrintStream printStream;

    @Before
    public void setUp() {
        this.printStream = mock(PrintStream.class);
        this.printer = Printer.sharedInstance;
        printer.setPrintStream(printStream);
    }

    @Test
    public void printMessageOutputsCorrectMessage() {
        String welcomeMessage = Strings.WELCOME_MESSAGE;

        printer.printMessage(welcomeMessage);

        verify(printStream).println(welcomeMessage);
    }

    @Test
    public void printerCorrectlyPrintsListOfStrings() {
        String book1 = "book1";
        String book2 = "book2";
        String book3 = "book3";
        List<String> books = Arrays.asList(book1, book2, book3);
        String format = "\t%s\n\t%s\n\t%s\n";
        String expectedOutput = String.format(format, book1, book2, book3);

        printer.printList(books);

        verify(printStream).println(expectedOutput);
    }

}