package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class BibliotecaApp {


    private boolean flag_quit;
    public Menu menu;

    public BibliotecaApp() {
        flag_quit = false;
        menu = new Menu();
    }

    public boolean getFlag_quit() {
        return flag_quit;
    }


    public void getWelcomeMessage() {
        System.out.print("WELCOME!!!\n");
    }


    public void getAllLibraryBook(List<Book> books) {
        StringBuilder builder = new StringBuilder();
        if(!(books == null) && !books.isEmpty()) {
            for (int i = 0; i < books.size(); i++) {
                if (!books.get(i).isCheckout()) {
                    builder.append(getBooksDetails(books.get(i)));
                    if (i < books.size() - 1) {
                        builder.append("\n");
                    }
                }
            }
            System.out.print(builder.toString());
            return;
        }
        System.out.print("There are no books!");
    }

    public String getBooksDetails(Book book) {
        return (book.getTitle()+", "+book.getAuthor()+", "+Integer.toString((book.getYear())));
    }

    public int validateMenuOptions(String menuOption) {
        for (int i=0; i<menu.getMenuOptions().size(); i++){
            if(menuOption.toUpperCase().equals(menu.getMenuOptions().get(i).toUpperCase())) {
                return i;
            }
        }
        return -1;
    }

    private int findBookOnLibraryByName(List<Book> books, String title) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().toUpperCase().equals(title.toUpperCase())) {
                return i;
            }
        }
        return -1;
    }

    private String readFromKeyboard(){
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);
        String entry = null;
        System.out.print("Book title: ");
        try {
            entry = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entry;
    }

    public void getMenuOptions(String menuOption, List<Book> books) {
        int option = validateMenuOptions(menuOption);
        switch (option){
            case -1:    //Invalid option
                        System.out.println("Select a valid option!\n");
                        break;
            case 0:     //List Books
                        System.out.println();
                        getAllLibraryBook(books);
                        System.out.print("\n\n");
                        break;
            case 1:     //Checkout Book
                        checkoutBook(books);
                        break;
            case 2:     //Return Book
                        returnBook(books);
                        break;
            case 3:     //Quit
                        flag_quit = true;
                        System.out.println("Bye User!");
                        break;
            default:    break;
        }
    }

    private void checkoutBook(List<Book> books) {
        String title = readFromKeyboard();
        setCheckoutBookByName(books,title);
    }

    public void setCheckoutBookByName(List<Book> books, String title) {
        int index = findBookOnLibraryByName(books, title);

        if(index >=0 && !books.get(index).isCheckout()) {
            books.get(index).setCheckout(true);

            System.out.println("Thank you! Enjoy the book.\n");
        }
        else {
            System.out.println("That book is not available.\n");
        }
    }

    private void returnBook(List<Book> books) {
        String title = readFromKeyboard();
        returnBookByName(books,title);
    }


    public void returnBookByName(List<Book> books, String title) {
        int index = findBookOnLibraryByName(books, title);

        if(index >=0 && books.get(index).isCheckout()) {
            books.get(index).setCheckout(false);
            System.out.println("Thank you for returning the book.\n");
        }
        else {
            System.out.println("That is not a valid book to return.\n");
        }

    }

}
