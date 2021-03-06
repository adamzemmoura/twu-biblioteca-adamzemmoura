package com.twu.biblioteca.resources;

public class Strings {

    // welcome and goodbye messages
    public static final String WELCOME_MESSAGE = "📚📚 Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore! 📚📚";
    public static final String FAREWELL_MESSAGE = "Thanks for using Biblioteca! 👋😄";

    // headers
    public static final String BOOK_LIST_HEADER = "Here are all the books in the Library : ";
    public static final String MOVIE_LIST_HEADER = "Here are all the movies in the Library : ";
    public static final String MENU_HEADER = "Main menu : ";
    public static final String ERROR_MESSAGE_INVALID_SELECTION = "Please select a valid option!";
    public static final String ERROR_MESSAGE_TOO_MANY_LOGIN_ATTEMPTS = "Too many login attempts. Goodbye!";
    public static final String CURRENT_RENTALS_HEADER = "Here is a list of the current rentals : ";
    public static final String SHOW_USER_INFO_HEADER = "Here is your personal information : ";

    // menu titles
    public static final String MENU_OPTION_TITLE_SHOW_ALL_BOOKS = "Show all books";
    public static final String MENU_OPTION_TITLE_CHECKOUT_BOOK = "Checkout a book";
    public static final String MENU_OPTION_TITLE_CHECKIN_BOOK = "Check in book";
    public static final String MENU_OPTION_TITLE_QUIT = "Quit";
    public static final String MENU_OPTION_TITLE_SHOW_ALL_MOVIES = "Show all movies";
    public static final String MENU_OPTION_TITLE_CHECKOUT_MOVIES = "Checkout a movie";
    public static final String MENU_OPTION_TITLE_SHOW_CURRENT_RENTALS = "Show current rentals";
    public static final String MENU_OPTION_TITLE_SHOW_YOUR_INFORMATION = "Check your personal information";

    // Input prompts
    public static final String MENU_SELECTION_PROMPT = "Please enter a number : ";
    public static final String MENU_OPTION_CHECKOUT_BOOK_PROMPT = "Please enter the title of the book you would like to checkout : ";
    public static final String MENU_OPTION_CHECKIN_BOOK_PROMPT = "Please enter the title of the book you would like to checkin : ";
    public static final String MENU_OPTION_TITLE_CHECKOUT_MOVIE_PROMPT = "Please enter the title of the movie you would like to checkout : ";
    public static final String LOGIN_PROMPT = "Please enter your library number to login : ";
    public static final String PASSWORD_PROMPT = "Please enter your password : ";

    // user feedback messages
    public static final String SUCCESSFUL_BOOK_CHECKOUT_MESSAGE = "Thank you! Enjoy the book";
    public static final String UNSUCCESSFUL_BOOK_CHECKOUT_MESSAGE = "Sorry, that book is not available";
    public static final String SUCCESSFUL_MOVIE_CHECKOUT_MESSAGE = "Thank you! Enjoy the movie";
    public static final String UNSUCCESSFUL_MOVIE_CHECKOUT_MESSAGE = "Sorry, that movie is not available";
    public static final String SUCCESSFUL_BOOK_CHECKIN_MESSAGE = "Thank you for returning the book";
    public static final String UNSUCCESSFUL_BOOK_CHECKIN_MESSAGE = "This is not a valid book to return";
    public static final String LOGIN_SUCCESS_MESSAGE = "Login was successful. Welcome back ";
    public static final String FAILED_LOGIN_ATTEMPT = "Login details were incorrect, please try again";

    // star ratings
    public static final String FIVE_STAR_RATING = "⭐⭐⭐⭐⭐️";
    public static final String FOUR_STAR_RATING = "⭐⭐⭐⭐️";
    public static final String THREE_STAR_RATING = "⭐⭐⭐";
    public static final String TWO_STAR_RATING = "⭐⭐";
    public static final String ONE_STAR_RATING = "⭐";
}
