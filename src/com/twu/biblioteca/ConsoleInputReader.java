package com.twu.biblioteca;

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

    public String attemptToReadString() throws IOException {
        return bufferedReader.readLine();
    }

    public int attemptToReadInt() throws IOException, NumberFormatException {
        String inputString = bufferedReader.readLine();
        return Integer.parseInt(inputString);
    }
}
