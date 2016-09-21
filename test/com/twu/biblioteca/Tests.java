package com.twu.biblioteca;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Tests {

    @Test
    public void shouldWelcomeCustomer(){
        assertEquals("Welcome", BibliotecaApp.getWelcomeMessage());
    }
/*
    @Test
    public void shouldShowAllLibraryBooks(){
        List<String> allLibraryBooks = new ArrayList<>();
        allLibraryBooks.add("The host");
        allLibraryBooks.add("O alquimista");
        assertEquals("The host, O alquimista", BibliotecaApp.getAllLibraryBooks(allLibraryBooks));
    }

    @Test
    public void shouldShowDifferentLibraryBooks(){
        List<String> allLibraryBooks = new ArrayList<>();
        allLibraryBooks.add("Harry Potter");
        allLibraryBooks.add("TDD by example");
        allLibraryBooks.add("Ben Hur");
        assertEquals("Harry Potter, TDD by example, Ben Hur", BibliotecaApp.getAllLibraryBooks(allLibraryBooks));
    }
*/
    @Test
    public void shouldAlertCustomerWhenTheBookListIsEmpty(){
        List<Book> books = new ArrayList<>();
        assertEquals("There are no books!", BibliotecaApp.getAllLibraryBooks(books));
    }

    @Test
    public void shouldAlertCustomerWhenTheBookListIsNull(){
        List<Book> books = null;
        assertEquals("There are no books!", BibliotecaApp.getAllLibraryBooks(books));
    }

    @Test
    public void shouldShowBookDetails(){
        Book book = new Book("Harry Potter","Rowling",2000);
        assertEquals("Harry Potter, Rowling, 2000", BibliotecaApp.getBooksDetails(book));

    }

    @Test
    public void shouldShowDetailsOfLibraryBooks(){
        List<Book> books = new ArrayList<>();
        books.add(new Book("Harry Potter","Rowling",2000));
        books.add(new Book("O alquimista","Paulo Coelho",1988));
        assertEquals("Harry Potter, Rowling, 2000\n" +
                "O alquimista, Paulo Coelho, 1988", BibliotecaApp.getAllLibraryBooks(books));

    }

    @Test
    public void shouldShowMenuOptionListBooks(){
        assertEquals("List Books", BibliotecaApp.getMenuOptions());
    }

    @Test
    public void shouldShowAllBooksWhenMenuOptionListBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Harry Potter","Rowling",2000));
        books.add(new Book("O alquimista","Paulo Coelho",1988));
        assertEquals("Harry Potter, Rowling, 2000\n" +
                "O alquimista, Paulo Coelho, 1988", BibliotecaApp.getMenuOptions("List Books",books));
    }


    @Test
    public void shouldShowMenuInvalidOption(){
        String menuOption = "List Authors";
        assertEquals("Select a valid option!", BibliotecaApp.getMenuOptions(menuOption));
    }

}
