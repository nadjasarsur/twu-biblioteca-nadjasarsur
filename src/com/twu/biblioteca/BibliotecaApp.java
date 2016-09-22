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
        System.out.println(getWelcomeMessage() + "\n");
        menu = new Menu();
    }

    public boolean getFlag_quit() {
        return flag_quit;
    }


    public String getWelcomeMessage() {
        return "WELCOME!!!";
    }


    public String getAllLibraryBook(List<Book> books) {
        StringBuilder builder = new StringBuilder();
        if(books == null || books.isEmpty()) {
            return "There are no books!";
        }
        else {
            for (int i = 0; i < books.size(); i++) {
                if(!books.get(i).isCheckout()) {
                    builder.append(getBooksDetails(books.get(i)));
                    if (i < books.size() - 1) {
                        builder.append("\n");
                    }
                }
            }
            return builder.toString();
        }
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

    public String getMenuOptions(String menuOption, List<Book> books) {
        int option = validateMenuOptions(menuOption);
        switch (option){
            case -1:    //Invalid option
                        System.out.println("Select a valid option!");
                        return "Select a valid option!";
            case 0:     //List Books
                        System.out.println("\nBooks on the library: " + (getAllLibraryBook(books) + "\n"));
                        return getAllLibraryBook(books);
            case 1:     //Checkout Book
                        checkoutBook(books);
                        return getAllLibraryBook(books);
            case 2:     //Return Book
                        returnBook(books);
                        return getAllLibraryBook(books);
            case 3:     //Quit
                        flag_quit = true;
                        System.out.println("Bye User");
                        return "Exit!";
        }
        
        return "The option is valid!";
    }

    private void checkoutBook(List<Book> books) {
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);
        String title = null;
        System.out.print("Book title: ");
        try {
            title = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setCheckoutBookByName(books,title);
    }


    private void returnBook(List<Book> books) {
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);
        String title = null;
        System.out.print("Book title: ");
        try {
            title = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        returnBookByName(books,title);
    }

    public String setCheckoutBookByName(List<Book> books, String title) {
        int index = findBookOnLibraryByName(books, title);
        try{
            books.get(index).setCheckout(true);
            showCheckoutMessage(books,index);
        } catch (Exception e) {
            showCheckoutMessage(books,index);
        }
        return getAllLibraryBook(books);

    }

    private int findBookOnLibraryByName(List<Book> books, String title) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().toUpperCase().equals(title.toUpperCase())) {
                return i;
            }
        }
        return -1;
    }


    public String showCheckoutMessage(List<Book> books, int index) {
        if(index >= 0 && books.get(index).isCheckout()){
            System.out.println("Thank you! Enjoy the book\n");
            return "Thank you! Enjoy the book";
        }
        System.out.println("That book is not available.\n");
        return "That book is not available.";
    }

    public String returnBookByName(List<Book> books, String title) {
        int index = findBookOnLibraryByName(books, title);
        try{
            books.get(index).setCheckout(false);
            showReturnMessage(books,index);
        } catch (Exception e) {
            showReturnMessage(books,index);

        }
        return getAllLibraryBook(books);

    }

    public String showReturnMessage(List<Book> books, int index) {
        if(index >= 0 && !books.get(index).isCheckout()){
            System.out.println("Thank you for returning the book.\n");
            return "Thank you for returning the book.";
        }
        System.out.println("That is not a valid book to return.\n");
        return "That is not a valid book to return.";
    }


}
