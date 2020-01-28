package com.twu.biblioteca;

import com.twu.biblioteca.exceptions.InvalidInputException;
import com.twu.biblioteca.resources.Strings;

import java.util.List;
import java.util.stream.Collectors;

public class Menu {

    private List<MenuOption> options;
    private Printer printer = Printer.sharedInstance;

    public Menu(List<MenuOption> menuOptions) {
        this.options = menuOptions;
    }

    public void handleUserSelection() {
        try {
            tryToMakeSelectionFromUserInput();
        } catch (InvalidInputException iie) {
            printer.printMessage(Strings.ERROR_MESSAGE_INVALID_SELECTION);
        }
    }

    public void display() {
        printer.printMessage(Strings.MENU_HEADER);
        List<String> menuOptionTitles = getMenuOptionTitles();
        printer.printNumberList(menuOptionTitles);
    }

    private List<String> getMenuOptionTitles() {
        return options.stream()
                .map(option -> option.getTitle() )
                .collect(Collectors.toList());
    }

    private void tryToMakeSelectionFromUserInput() throws InvalidInputException {
        int userInput = ConsoleInputReader.sharedInstance.attemptToReadInt(Strings.MENU_SELECTION_PROMPT);
        int index = userInput - 1;
        checkIndexIsValid(index);
        MenuOption selectedOption = options.get(index);
        selectedOption.select();
    }

    private void checkIndexIsValid(int index) throws InvalidInputException {
        if (index < 0 || index > options.size() - 1) {
            throw new InvalidInputException("Valid number but out of bounds");
        }
    }
}
