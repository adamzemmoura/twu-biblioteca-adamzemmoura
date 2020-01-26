package com.twu.biblioteca;

public class MenuOption {

    private String title;
    private Selectable action;

    public MenuOption(String title, Selectable action) {
        this.title = title;
        this.action = action;
    }

    public void select() {
        action.select();
    }
}
