package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Tests {


    BibliotecaApp biblio = new BibliotecaApp();



    @Test
    public void shouldWelcomeCustomer(){
        assertEquals("Welcome", biblio.getWelcomeMessage());
    }

    @Test
    public void shouldAlertCustomerWhenTheBookListIsEmpty(){
        List<Book> books = new ArrayList<>();
        assertEquals("There are no books!", biblio.getAllLibraryBooks(books));
    }

    @Test
    public void shouldAlertCustomerWhenTheBookListIsNull(){
        List<Book> books = null;
        assertEquals("There are no books!", biblio.getAllLibraryBooks(books));
    }

    @Test
    public void shouldShowBookDetails(){
        Book book = new Book("Harry Potter","Rowling",2000);
        assertEquals("Harry Potter, Rowling, 2000", biblio.getBooksDetails(book));

    }

    @Test
    public void shouldShowDetailsOfLibraryBooks(){
        List<Book> books = new ArrayList<>();
        books.add(new Book("Harry Potter","Rowling",2000));
        books.add(new Book("O alquimista","Paulo Coelho",1988));
        assertEquals("Harry Potter, Rowling, 2000\n" +
                "O alquimista, Paulo Coelho, 1988", biblio.getAllLibraryBooks(books));

    }

    @Test
    public void shouldShowMenuOptionListBooks(){
        assertEquals("List Books", biblio.getMenuOptions());
    }

    @Test
    public void shouldShowAllBooksWhenMenuOptionListBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Harry Potter","Rowling",2000));
        books.add(new Book("O alquimista","Paulo Coelho",1988));
        assertEquals("Harry Potter, Rowling, 2000\n" +
                "O alquimista, Paulo Coelho, 1988", biblio.getMenuOptions("List Books",books));
    }


    @Test
    public void shouldShowMenuInvalidOption(){
        String menuOption = "List Authors";
        assertEquals("Select a valid option!", biblio.getMenuOptions(menuOption, null));
    }

    @Test
    public void shouldQuitWhenMenuOptionQuit(){
        String menuOption = "Quit";
        assertEquals("Exit!", biblio.getMenuOptions(menuOption, null));
    }


}
