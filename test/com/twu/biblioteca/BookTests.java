package com.twu.biblioteca;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class BookTests {

    BibliotecaApp biblio;
    List<Book> books;
    ByteArrayOutputStream answer = new ByteArrayOutputStream();

    @Before
    public void setUp(){
        System.setOut(new PrintStream(answer));
        biblio = new BibliotecaApp();
        books = new ArrayList<Book>();
        books.add(new Book("Harry Potter","Rowling",2000));
        books.add(new Book("O alquimista","Paulo Coelho",1988));
    }

    @After
    public void tearDown() {
        System.setOut(null);
    }

    @Test
    public void shouldWelcomeCustomer(){
        biblio.getWelcomeMessage();
        assertEquals("WELCOME!!!\n", answer.toString());
    }

    @Test
    public void shouldAlertCustomerWhenTheBookListIsEmpty(){
        List<Book> noBooks = new ArrayList<Book>();
        biblio.getAllLibraryBook(noBooks);
        assertEquals("There are no books!", answer.toString());
    }

    @Test
    public void shouldAlertCustomerWhenTheBookListIsNull(){
        List<Book> noBooks = null;
        biblio.getAllLibraryBook(noBooks);
        assertEquals("There are no books!", answer.toString());
    }

    @Test
    public void shouldShowDetailsOfOneBook(){
        assertEquals("Harry Potter, Rowling, 2000", biblio.getBooksDetails(books.get(0)));

    }

    @Test
    public void shouldShowDetailsOfAllLibraryBook(){
        biblio.getAllLibraryBook(books);
        assertEquals("Harry Potter, Rowling, 2000\n" +
                "O alquimista, Paulo Coelho, 1988", answer.toString());

    }

    @Test
    public void shouldShowValidateMenuOptions(){
        Menu menu = new Menu();
        assertEquals("List Books", menu.getMenuOptions().get(0));
        assertEquals("Checkout Book", menu.getMenuOptions().get(1));
        assertEquals("Return Book", menu.getMenuOptions().get(2));
        assertEquals("Quit", menu.getMenuOptions().get(3));
    }

    @Test
    public void shouldShowWarningMessageWhenMenuOptionIsInvalid(){
        String menuOption = "List Authors";
        biblio.getMenuOptions(menuOption, null);
        assertEquals("Select a valid option!\n\n",answer.toString());
    }

    @Test
    public void shouldShowAllBooksWhenMenuOptionListBooksIsSelected() {
        biblio.getMenuOptions("List Books",books);
        assertEquals("\nHarry Potter, Rowling, 2000\n" +
                "O alquimista, Paulo Coelho, 1988\n\n", answer.toString());
    }

    @Test
    public void shouldQuitExecutionWhenMenuOptionQuitIsSelected(){
        String menuOption = "Quit";
        biblio.getMenuOptions(menuOption, null);
        assertEquals("Bye User!\n", answer.toString());
    }

    @Test
    public void shouldCheckoutBookAndVerifyItIsNotListed(){
        biblio.setCheckoutBookByName(books,books.get(0).getTitle());
        answer.reset();
        biblio.getAllLibraryBook(books);
        assertEquals("O alquimista, Paulo Coelho, 1988",answer.toString());
    }

    @Test
    public void shouldCheckoutBookAndVerifyParameterCheckoutForBook(){
        biblio.setCheckoutBookByName(books,books.get(0).getTitle());
        assertTrue(books.get(0).isCheckout());
    }

    @Test
    public void shouldVerifySuccessfulCheckout(){
        biblio.setCheckoutBookByName(books,books.get(0).getTitle());
        assertEquals("Thank you! Enjoy the book.\n\n", answer.toString());
    }

    @Test
    public void shouldShowUnsuccessfulMessageWhenTheBooksTitleIsWrong(){
        biblio.setCheckoutBookByName(books,"HHHarry PPPotter :)");
        assertEquals("That book is not available.\n\n", answer.toString());
    }

    @Test
    public void shouldShowUnsuccessfulMessageWhenBookIsAlreadyCheckedout(){
        biblio.setCheckoutBookByName(books,books.get(0).getTitle());
        answer.reset();
        biblio.setCheckoutBookByName(books,books.get(0).getTitle());
        assertEquals("That book is not available.\n\n", answer.toString());
    }

    @Test
    public void shouldReturnBookAndVerifyParameterCheckoutForBook(){
        biblio.setCheckoutBookByName(books,books.get(0).getTitle());
        biblio.returnBookByName(books,books.get(0).getTitle());
        assertFalse(books.get(0).isCheckout());
    }

    @Test
    public void shouldShowReturnedBookOnTheListBook() {
        biblio.setCheckoutBookByName(books,books.get(0).getTitle());
        biblio.returnBookByName(books,books.get(0).getTitle());
        answer.reset();
        biblio.getMenuOptions("List Books",books);
        assertEquals("\nHarry Potter, Rowling, 2000\n" +
                "O alquimista, Paulo Coelho, 1988\n\n", answer.toString());
    }


    @Test
    public void shouldVerifySuccessfulReturn(){
        biblio.setCheckoutBookByName(books,books.get(0).getTitle());
        answer.reset();
        biblio.returnBookByName(books,books.get(0).getTitle());
        System.out.println("Thank you for returning the book.\n");
    }

    @Test
    public void shouldVerifyUnsuccessfulReturn(){
        biblio.setCheckoutBookByName(books,books.get(0).getTitle());
        biblio.returnBookByName(books,books.get(0).getTitle());
        answer.reset();
        biblio.returnBookByName(books,books.get(0).getTitle());
        assertEquals("That is not a valid book to return.\n\n", answer.toString());

    }

    @Test
    public void shouldVerifyUnsuccessfulReturn2(){
        biblio.setCheckoutBookByName(books,books.get(0).getTitle());
        answer.reset();
        biblio.returnBookByName(books,"Harry Potter :)");
        assertEquals("That is not a valid book to return.\n\n", answer.toString());
    }


}
