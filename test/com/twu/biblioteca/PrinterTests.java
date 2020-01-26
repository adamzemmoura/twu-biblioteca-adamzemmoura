package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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

}