package com.twu.biblioteca;

import java.io.PrintStream;

public class Printer {

    public static Printer sharedInstance = new Printer();
    private PrintStream printStream;

    private Printer() {
        printStream = System.out;
    }

    public void setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void printMessage(String message) {
        printStream.println(message);
    }
}
