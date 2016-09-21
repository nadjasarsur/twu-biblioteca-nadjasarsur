package com.twu.biblioteca;

import java.util.List;

public class BibliotecaApp {


    private boolean flag_quit;
    public Menu menu;

    public BibliotecaApp() {
        flag_quit = false;
        System.out.println(getWelcomeMessage());
        menu = new Menu();
    }

    public boolean getFlag_quit() {
        return flag_quit;
    }


    public void setFlag_quit(boolean flag_quit) {
        this.flag_quit = flag_quit;
    }


    public String getWelcomeMessage() {
        return "Welcome";
    }


    public String getAllLibraryBooks(List<Book> books) {
        StringBuilder builder = new StringBuilder();
        if(books == null || books.isEmpty()) {
            return "There are no books!";
        }
        else {
            for (int i = 0; i < books.size(); i++) {
                builder.append(getBooksDetails(books.get(i)));
                if(i < books.size()-1 ) {
                    builder.append("\n");
                }
            }
            return builder.toString();
        }
    }

    public String getBooksDetails(Book book) {
        return (book.getTitle()+", "+book.getAuthor()+", "+Integer.toString((book.getYear())));
    }

    public String getMenuOptions() {
        return "List Books";
    }

    public String getMenuOptions(String menuOption, List<Book> books) {
        if(!menuOption.equals("List Books") && !menuOption.equals("Quit")){
            System.out.println("Select a valid option!");
            return "Select a valid option!";
        }
        if(menuOption.equals("List Books")) {
            System.out.println(getAllLibraryBooks(books));
            return getAllLibraryBooks(books);
        }
        if(menuOption.equals("Quit")) {
            flag_quit = true;
            System.out.println("Bye User");
            return "Exit!";
        }
        return "The option is valid!";
    }




}
