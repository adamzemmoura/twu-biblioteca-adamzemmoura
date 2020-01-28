package com.twu.biblioteca;

import com.twu.biblioteca.exceptions.InvalidInputException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInputReader {

    public static ConsoleInputReader sharedInstance = new ConsoleInputReader();
    private BufferedReader bufferedReader;

    private ConsoleInputReader() {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        this.bufferedReader = new BufferedReader(inputStreamReader);
    }

    public void setBufferedReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public String attemptToReadString(String prompt) throws IOException {
        Printer.sharedInstance.printMessage(prompt);
        return bufferedReader.readLine();
    }

    public int attemptToReadInt(String prompt) throws InvalidInputException {
        Printer.sharedInstance.printMessage(prompt);
        try {
            String inputString = bufferedReader.readLine();
            return Integer.parseInt(inputString);
        } catch (Exception e) {
            throw new InvalidInputException(e.getLocalizedMessage());
        } 
    }
}
