package com.twu.biblioteca;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BibliotecaAppTests {

    private BibliotecaApp biblio;
    private List<Book> books;
    private List<Movie> movies;
    private List<Account> users;
    private ByteArrayOutputStream answer;

    @Before
    public void setUp(){
        answer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(answer));
        biblio = new BibliotecaApp();

        books = new ArrayList<Book>();
        books.add(new Book("Harry Potter","Rowling",2000));
        books.add(new Book("O alquimista","Paulo Coelho",1988));

        movies = new ArrayList<Movie>();
        movies.add(new Movie("Passengers",2016,"Morten Tyldum"));
        movies.add(new Movie("Titanic",1997,"James Cameron"));

        users = new ArrayList<Account>();
        users.add(new Account("1234-5678","CrazyPassword","Nadja Sarsur","nsarsur@thoughtworks.com","9999-9999"));
        users.add(new Account("5555-2222","HelloWorld!"," Brad Pitty","bpitty@thoughtworks.com","9999-9999"));

    }

    @After
    public void tearDown() {
        System.setOut(null);
    }


    /*
    ##################### TESTS TO VERIFY FUNCTIONALITY #####################
    */

    //WELCOME USER
    @Test
    public void shouldWelcomeCustomer(){
        biblio.getWelcomeMessage();
        assertEquals("WELCOME!!!\n", answer.toString());
    }

    //BOOK LIST EMPTY
    @Test
    public void shouldAlertCustomerWhenTheBookListIsEmpty(){
        List<Book> noBooks = new ArrayList<Book>();
        biblio.listItems(noBooks);
        assertEquals("There are no items!", answer.toString());
    }

    //MOVIE LIST EMPTY
    @Test
    public void shouldAlertCustomerWhenTheMovieListIsEmpty(){
        List<Movie> noMovies = new ArrayList<Movie>();
        biblio.listItems(noMovies);
        assertEquals("There are no items!", answer.toString());
    }

    //BOOK LIST NULL
    @Test
    public void shouldAlertCustomerWhenTheBookListIsNull(){
        List<Book> noBooks = null;
        biblio.listItems(noBooks);
        assertEquals("There are no items!", answer.toString());
    }

    //MOVIE LIST NULL
    @Test
    public void shouldAlertCustomerWhenTheMovieListIsNull(){
        List<Movie> noMovies = null;
        biblio.listItems(noMovies);
        assertEquals("There are no items!", answer.toString());
    }


    /*
    --------- LIST ---------
    */

    //BOOK LIST DETAIL
    @Test
    public void shouldShowDetailsOfAllLibraryBook(){
        biblio.listItems(books);
        assertEquals("Harry Potter, Rowling, 2000\n" +
                "O alquimista, Paulo Coelho, 1988", answer.toString());

    }

    //MOVIE LIST DETAIL
    @Test
    public void shouldShowDetailsOfAllLibraryMovie(){
        biblio.listItems(movies);
        assertEquals("Passengers, Morten Tyldum, 2016, 0\n" +
                "Titanic, James Cameron, 1997, 0", answer.toString());
    }

    /*
    --------- CHECKOUT ---------
    */

    //SUCCESSFUL BOOK CHECKOUT
    @Test
    public void shouldVerifySuccessfulCheckoutBookMessage(){
        biblio.setCheckoutItemByName(books,books.get(0).getTitle(),users.get(0));
        assertEquals("Thank you! Enjoy the item.\nBook checked out by Nadja Sarsur\n\n", answer.toString());
    }

    //SUCCESSFUL MOVIE CHECKOUT
    @Test
    public void shouldVerifySuccessfulCheckoutMovieMessage(){
        biblio.setCheckoutItemByName(movies,movies.get(0).getTitle(),users.get(0));
        assertEquals("Thank you! Enjoy the item.\nBook checked out by Nadja Sarsur\n\n", answer.toString());
    }

    //UNSUCCESSFUL BOOK CHECKOUT
    @Test
    public void shouldShowUnsuccessfulMessageWhenTheBooksTitleIsWrong(){
        biblio.setCheckoutItemByName(books,"HHHarry PPPotter :)",users.get(0));
        assertEquals("That item is not available.\n\n", answer.toString());
    }

    //UNSUCCESSFUL MOVIE CHECKOUT
    @Test
    public void shouldShowUnsuccessfulMessageWhenTheMoviesTitleIsWrong(){
        biblio.setCheckoutItemByName(movies,"TITAANIC :)",users.get(0));
        assertEquals("That item is not available.\n\n", answer.toString());
    }

    //UNSUCCESSFUL BOOK CHECKOUT
    @Test
    public void shouldShowUnsuccessfulMessageWhenBookIsAlreadyCheckedOut(){
        biblio.setCheckoutItemByName(books,books.get(0).getTitle(),users.get(0));
        answer.reset();
        biblio.setCheckoutItemByName(books,books.get(0).getTitle(),users.get(0));
        assertEquals("That item is not available.\n\n", answer.toString());
    }

    //UNSUCCESSFUL MOVIE CHECKOUT
    @Test
    public void shouldShowUnsuccessfulMessageWhenMovieIsAlreadyCheckedOut(){
        biblio.setCheckoutItemByName(movies,movies.get(0).getTitle(),users.get(0));
        answer.reset();
        biblio.setCheckoutItemByName(movies,movies.get(0).getTitle(),users.get(0));
        assertEquals("That item is not available.\n\n", answer.toString());
    }

    /*
    --------- RETURN ---------
    */

    //SUCCESSFUL BOOK RETURN
    @Test
    public void shouldVerifySuccessfulBookReturn(){
        biblio.setCheckoutItemByName(books,books.get(0).getTitle(),users.get(0));
        answer.reset();
        biblio.returnItemByName(books,books.get(0).getTitle(),users.get(0));
        System.out.println("Thank you for returning the item.\n");
    }

    //SUCCESSFUL MOVIE RETURN
    @Test
    public void shouldVerifySuccessfulMovieReturn(){
        biblio.setCheckoutItemByName(movies,movies.get(0).getTitle(),users.get(0));
        answer.reset();
        biblio.returnItemByName(movies,movies.get(0).getTitle(),users.get(0));
        System.out.println("Thank you for returning the item.\n");
    }

    //UNSUCCESSFUL BOOK RETURN
    @Test
    public void shouldVerifyUnsuccessfulBookReturnWhenBookIsNotCheckedOut(){
        biblio.returnItemByName(books,books.get(0).getTitle(),users.get(0));
        assertEquals("That is not a valid item to return.\n\n", answer.toString());
    }

    //UNSUCCESSFUL MOVIE RETURN
    @Test
    public void shouldVerifyUnsuccessfulMovieReturnWhenMovieIsNotCheckedOut(){
        biblio.returnItemByName(movies,movies.get(0).getTitle(),users.get(0));
        assertEquals("That is not a valid item to return.\n\n", answer.toString());
    }

    //UNSUCCESSFUL BOOK RETURN
    @Test
    public void shouldVerifyUnsuccessfulBookReturnWhenBooksTitleIsWrong() {
        biblio.setCheckoutItemByName(books, books.get(0).getTitle(),users.get(0));
        answer.reset();
        biblio.returnItemByName(books, "Harry Potter :)",users.get(0));
        assertEquals("That is not a valid item to return.\n\n", answer.toString());
    }

    //UNSUCCESSFUL BOOK RETURN
    @Test
    public void shouldVerifyUnsuccessfulMovieReturnWhenMoviesTitleIsWrong() {
        biblio.setCheckoutItemByName(movies, movies.get(0).getTitle(),users.get(0));
        answer.reset();
        biblio.returnItemByName(movies,"TITAANIC :)",users.get(0));
        assertEquals("That is not a valid item to return.\n\n", answer.toString());
    }

    /*
    ##################### TESTS TO VERIFY METHODS #####################
    */

    //FIND BOOK
    @Test
    public void shouldFindBookOnBookList(){
        int i = biblio.findItemOnLibraryByName(books,"HARRY POTTER");
        assertEquals(0,i);
    }

    //FIND MOVIE
    @Test
    public void shouldFindMovieOnMovieList(){
        int i = biblio.findItemOnLibraryByName(movies,"TITANIC");
        assertEquals(1,i);
    }

    //DON'T FIND BOOK
    @Test
    public void shouldNotFindBookOnBookList(){
        int i = biblio.findItemOnLibraryByName(books,"HAARRY POTTER");
        assertEquals(-1,i);
    }

    //DON'T FIND MOVIE
    @Test
    public void shouldNotFindMovieOnMovieList(){
        int i = biblio.findItemOnLibraryByName(movies,"TITAAANIC");
        assertEquals(-1,i);
    }
}
