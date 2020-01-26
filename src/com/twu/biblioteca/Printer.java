package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.List;

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

    public void printList(List<String> items) {
        StringBuilder sb = new StringBuilder();
        String format = "\t%s\n";
        items.stream().forEach(item -> sb.append( String.format(format, item)));
        printStream.println(sb.toString());
    }
}
