package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ConsoleInputReaderTests {

    private ConsoleInputReader consoleInputReader;
    BufferedReader bufferedReaderMock;
    @Before
    public void setUp() {
        bufferedReaderMock = mock(BufferedReader.class);
        consoleInputReader = ConsoleInputReader.sharedInstance;
        consoleInputReader.setBufferedReader(bufferedReaderMock);
    }

    @Test
    public void canReadStringFromTheConsole() throws Exception {
        String expectedInput = "word";
        when(bufferedReaderMock.readLine()).thenReturn(expectedInput);

        String input = consoleInputReader.attemptToGetStringInput();

        assertThat(input, is(expectedInput));
    }

}