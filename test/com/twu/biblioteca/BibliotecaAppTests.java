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
        biblio.setCheckoutItemByName(books,books.get(0).getTitle());
        assertEquals("Thank you! Enjoy the item.\n\n", answer.toString());
    }

    //SUCCESSFUL MOVIE CHECKOUT
    @Test
    public void shouldVerifySuccessfulCheckoutMovieMessage(){
        biblio.setCheckoutItemByName(movies,movies.get(0).getTitle());
        assertEquals("Thank you! Enjoy the item.\n\n", answer.toString());
    }

    //UNSUCCESSFUL BOOK CHECKOUT
    @Test
    public void shouldShowUnsuccessfulMessageWhenTheBooksTitleIsWrong(){
        biblio.setCheckoutItemByName(books,"HHHarry PPPotter :)");
        assertEquals("That item is not available.\n\n", answer.toString());
    }

    //UNSUCCESSFUL MOVIE CHECKOUT
    @Test
    public void shouldShowUnsuccessfulMessageWhenTheMoviesTitleIsWrong(){
        biblio.setCheckoutItemByName(movies,"TITAANIC :)");
        assertEquals("That item is not available.\n\n", answer.toString());
    }

    //UNSUCCESSFUL BOOK CHECKOUT
    @Test
    public void shouldShowUnsuccessfulMessageWhenBookIsAlreadyCheckedOut(){
        biblio.setCheckoutItemByName(books,books.get(0).getTitle());
        answer.reset();
        biblio.setCheckoutItemByName(books,books.get(0).getTitle());
        assertEquals("That item is not available.\n\n", answer.toString());
    }

    //UNSUCCESSFUL MOVIE CHECKOUT
    @Test
    public void shouldShowUnsuccessfulMessageWhenMovieIsAlreadyCheckedOut(){
        biblio.setCheckoutItemByName(movies,movies.get(0).getTitle());
        answer.reset();
        biblio.setCheckoutItemByName(movies,movies.get(0).getTitle());
        assertEquals("That item is not available.\n\n", answer.toString());
    }

    /*
    --------- RETURN ---------
    */

    //SUCCESSFUL BOOK RETURN
    @Test
    public void shouldVerifySuccessfulBookReturn(){
        biblio.setCheckoutItemByName(books,books.get(0).getTitle());
        answer.reset();
        biblio.returnItemByName(books,books.get(0).getTitle());
        System.out.println("Thank you for returning the item.\n");
    }

    //SUCCESSFUL MOVIE RETURN
    @Test
    public void shouldVerifySuccessfulMovieReturn(){
        biblio.setCheckoutItemByName(movies,movies.get(0).getTitle());
        answer.reset();
        biblio.returnItemByName(movies,movies.get(0).getTitle());
        System.out.println("Thank you for returning the item.\n");
    }

    //UNSUCCESSFUL BOOK RETURN
    @Test
    public void shouldVerifyUnsuccessfulBookReturnWhenBookIsNotCheckedOut(){
        biblio.returnItemByName(books,books.get(0).getTitle());
        assertEquals("That is not a valid item to return.\n\n", answer.toString());
    }

    //UNSUCCESSFUL MOVIE RETURN
    @Test
    public void shouldVerifyUnsuccessfulMovieReturnWhenMovieIsNotCheckedOut(){
        biblio.returnItemByName(movies,movies.get(0).getTitle());
        assertEquals("That is not a valid item to return.\n\n", answer.toString());
    }

    //UNSUCCESSFUL BOOK RETURN
    @Test
    public void shouldVerifyUnsuccessfulBookReturnWhenBooksTitleIsWrong() {
        biblio.setCheckoutItemByName(books, books.get(0).getTitle());
        answer.reset();
        biblio.returnItemByName(books, "Harry Potter :)");
        assertEquals("That is not a valid item to return.\n\n", answer.toString());
    }

    //UNSUCCESSFUL BOOK RETURN
    @Test
    public void shouldVerifyUnsuccessfulMovieReturnWhenMoviesTitleIsWrong() {
        biblio.setCheckoutItemByName(movies, movies.get(0).getTitle());
        answer.reset();
        biblio.returnItemByName(movies,"TITAANIC :)");
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
