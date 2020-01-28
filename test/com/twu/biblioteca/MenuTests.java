package com.twu.biblioteca;

import com.twu.biblioteca.factories.MenuOptionFactory;
import com.twu.biblioteca.resources.Strings;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class MenuTests {

    private Menu menu;

    @Before
    public void setUp() {
        List<MenuOption> menuOptions = Arrays.asList(
                MenuOptionFactory.createShowAllBooksMenuOption()
        );
        menu = new Menu(menuOptions);
    }

    @Test
    public void whenUserInputsNumberNotCorrespondingToMenuOptionThenErrorMessagePrintedToConsole() throws Exception {
        BufferedReader readerMock = mock(BufferedReader.class);
        ConsoleInputReader.sharedInstance.setBufferedReader(readerMock);
        PrintStream printStreamMock = mock(PrintStream.class);
        Printer.sharedInstance.setPrintStream(printStreamMock);
        when(readerMock.readLine()).thenReturn("0");

        menu.handleUserSelection();

        verify(printStreamMock).println(Strings.ERROR_MESSAGE_INVALID_SELECTION);
    }


}