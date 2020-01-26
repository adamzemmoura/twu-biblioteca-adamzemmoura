package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class ConsoleInputReaderTests {

    private ConsoleInputReader consoleInputReader;
    BufferedReader bufferedReaderMock;
    PrintStream printStream;
    private Printer printer;


    @Before
    public void setUp() {
        bufferedReaderMock = mock(BufferedReader.class);
        consoleInputReader = ConsoleInputReader.sharedInstance;
        consoleInputReader.setBufferedReader(bufferedReaderMock);
        printer = Printer.sharedInstance;
        printStream = mock(PrintStream.class);
        printer.setPrintStream(printStream);

    }

    @Test
    public void canReadStringFromTheConsole() throws Exception {
        String expectedInput = "word";
        when(bufferedReaderMock.readLine()).thenReturn(expectedInput);

        String input = consoleInputReader.attemptToReadString("prompt");

        assertThat(input, is(expectedInput));
    }

    @Test
    public void displaysPromptWhilstAwaitingUserStringInput() throws Exception {
        String prompt = "Please enter string : ";
        consoleInputReader.attemptToReadString(prompt);
        verify(printStream).println(prompt);
    }

    @Test
    public void displaysPromptWhilstAwaitingUserNumberInput() throws Exception {
        String prompt = "Please enter number : ";
        when(bufferedReaderMock.readLine()).thenReturn("10");
        consoleInputReader.attemptToReadInt(prompt);
        verify(printStream).println(prompt);
    }

    @Test
    public void canReadIntFromConsole() throws Exception {
        when(bufferedReaderMock.readLine()).thenReturn("1");
        int input1 = consoleInputReader.attemptToReadInt("prompt");

        when(bufferedReaderMock.readLine()).thenReturn("10");
        int input2 = consoleInputReader.attemptToReadInt("prompt");

        when(bufferedReaderMock.readLine()).thenReturn("100");
        int input3 = consoleInputReader.attemptToReadInt("prompt");

        assertThat(input1, is(1));
        assertThat(input2, is(10));
        assertThat(input3, is(100));
    }

    @Test (expected = InvalidInputException.class)
    public void attemptingToReadIntFromConsoleThrowsWhenNonInt() throws Exception {
        when(bufferedReaderMock.readLine()).thenReturn("word");
        int input1 = consoleInputReader.attemptToReadInt("prompt");
    }

}