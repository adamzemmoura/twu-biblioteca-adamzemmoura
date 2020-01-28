package com.twu.biblioteca;

import com.twu.biblioteca.resources.Strings;
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

    @Test
    public void printerCorrectlyPrintsNumberedListOfStrings() {
        String item1 = "item1";
        String item2 = "item2";
        String item3 = "item3";
        List<String> items = Arrays.asList(item1, item2, item3);
        String expectedOutput = String.format("\t1. %s\n\t2. %s\n\t3. %s\n", item1, item2, item3);

        printer.printNumberList(items);

        verify(printStream).println(expectedOutput);
    }

}