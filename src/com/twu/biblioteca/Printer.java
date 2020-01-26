package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.List;

public class Printer {

    public static Printer sharedInstance = new Printer();
    private PrintStream printStream;
    private StringBuilder stringBuilder;

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
        stringBuilder = new StringBuilder();
        String format = "\t%s\n";
        items.stream().forEach(item -> stringBuilder.append( String.format(format, item)));
        printStream.println(stringBuilder.toString());
    }

    public void printNumberList(List<String> items) {
        stringBuilder = new StringBuilder();
        String format = "\t%d. %s\n";

        for (int i = 0; i < items.size(); i++) {
            String item = items.get(i);
            int numberForDisplay = i + 1;
            stringBuilder.append(String.format(format, numberForDisplay, item));

        }

        printMessage(stringBuilder.toString());


    }
}
