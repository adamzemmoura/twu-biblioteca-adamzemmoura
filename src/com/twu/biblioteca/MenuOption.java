package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.Selectable;

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

    public String getTitle() {
        return title;
    }
}
