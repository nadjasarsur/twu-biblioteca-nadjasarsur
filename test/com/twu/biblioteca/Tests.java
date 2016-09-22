package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class Tests {

    BibliotecaApp biblio;
    List<Book> books;

    @Before
    public void setUp(){
        biblio = new BibliotecaApp();
        books = new ArrayList<Book>();
        books.add(new Book("Harry Potter","Rowling",2000));
        books.add(new Book("O alquimista","Paulo Coelho",1988));
    }

    @Test
    public void shouldWelcomeCustomer(){
        assertEquals("WELCOME!!!", biblio.getWelcomeMessage());
    }

    @Test
    public void shouldAlertCustomerWhenTheBookListIsEmpty(){
        List<Book> noBooks = new ArrayList<Book>();
        assertEquals("There are no books!", biblio.getAllLibraryBooks(noBooks));
    }

    @Test
    public void shouldAlertCustomerWhenTheBookListIsNull(){
        List<Book> noBooks = null;
        assertEquals("There are no books!", biblio.getAllLibraryBooks(noBooks));
    }

    @Test
    public void shouldShowBookDetails(){
        Book oneBook = new Book("Harry Potter","Rowling",2000);
        assertEquals("Harry Potter, Rowling, 2000", biblio.getBooksDetails(oneBook));

    }

    @Test
    public void shouldShowDetailsOfLibraryBooks(){
        assertEquals("Harry Potter, Rowling, 2000\n" +
                "O alquimista, Paulo Coelho, 1988", biblio.getAllLibraryBooks(books));

    }

    @Test
    public void shouldShowMenuOptionListBooks(){
        assertEquals("List Books", biblio.getMenuOptions());
    }

    @Test
    public void shouldShowAllBooksWhenMenuOptionListBooks() {
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


    @Test
    public void shouldCheckoutBookByNameAndVerifyItIsNotListed(){
        biblio.setCheckoutBookByName(books,books.get(0).getTitle());
        assertEquals("O alquimista, Paulo Coelho, 1988",biblio.getAllLibraryBooks(books));
    }

    @Test
    public void shouldCheckoutBookAndVerifyParameterCheckoutFromBook(){
        biblio.setCheckoutBookByName(books,books.get(0).getTitle());
        assertTrue(books.get(0).isCheckout());
    }

    @Test
    public void shouldVerifySuccessfulCheckout(){
        biblio.setCheckoutBookByName(books,"Harry Potter");
        assertEquals("Thank you! Enjoy the book", biblio.showCheckoutMessage(books,0));
    }

    @Test
    public void shouldVerifyUnsuccessfulCheckout(){
        biblio.setCheckoutBookByName(books,"HHHarry PPPotter :)");
        assertEquals("That book is not available.", biblio.showCheckoutMessage(books,0));
    }

    @Test
    public void shouldReturnBookAndVerifyParameterCheckoutFromBook(){
        biblio.setCheckoutBookByName(books,books.get(0).getTitle());
        biblio.returnBookByName(books,books.get(0).getTitle());
        assertFalse(books.get(0).isCheckout());
    }

    @Test
    public void shouldReturnBookAndAddBookToListBook() {
        biblio.setCheckoutBookByName(books,books.get(0).getTitle());
        biblio.returnBookByName(books,books.get(0).getTitle());
        assertEquals("Harry Potter, Rowling, 2000\n" +
                "O alquimista, Paulo Coelho, 1988", biblio.getMenuOptions("List Books",books));
    }

    @Test
    public void shouldVerifySuccessfulReturn(){
        biblio.setCheckoutBookByName(books,books.get(0).getTitle());
        biblio.returnBookByName(books,books.get(0).getTitle());
        assertEquals("Thank you for returning the book.", biblio.showReturnMessage(books,0));

    }

    @Test
    public void shouldVerifyUnsuccessfulReturn(){
        biblio.setCheckoutBookByName(books,books.get(0).getTitle());
        biblio.returnBookByName(books,"Harry Potter :)");
        assertEquals("That is not a valid book to return.", biblio.showReturnMessage(books,0));
    }

}
