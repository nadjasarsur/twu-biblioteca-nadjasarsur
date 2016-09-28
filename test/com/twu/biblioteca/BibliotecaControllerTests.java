package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by nsarsur on 9/27/16.
 */
public class BibliotecaControllerTests {

    private Menu menu;
    private BibliotecaController controller;
    private ByteArrayOutputStream answer;
    private List<Book> books;
    private List<Movie> movies;
    private ArrayList<Account> users;


    @Before
    public void setUp(){
        answer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(answer));
        books = new ArrayList<Book>();
        books.add(new Book("Harry Potter","Rowling",2000));
        books.add(new Book("O alquimista","Paulo Coelho",1988));

        movies = new ArrayList<Movie>();
        movies.add(new Movie("Passengers",2016,"Morten Tyldum"));
        movies.add(new Movie("Titanic",1997,"James Cameron"));

        users = new ArrayList<Account>();
        users.add(new Account("1234-5678","CrazyPassword","Nadja Sarsur","nsarsur@thoughtworks.com","9999-9999"));
        users.add(new Account("5555-2222","HelloWorld!"," Brad Pitty","bpitty@thoughtworks.com","9999-9999"));

        Login login = new Login(users);
        controller = new BibliotecaController(books,movies,login);

        controller.setUserLogged(users.get(0));
        menu = new Menu();
    }

    @After
    public void tearDown() {
        System.setOut(null);
    }

    /*
    ##################### TESTS TO VERIFY FUNCTIONALITY #####################
    */


    /*
    --------- QUIT ---------
    */

    @Test
    public void shouldQuitExecutionWhenMenuOptionQuitIsSelected(){
        controller.executeMenu(5, 0, "", null, null);
        assertEquals("Bye User!\n", answer.toString());
    }

    /*
    --------- LOGIN ---------
    */

    @Test
    public void shouldValidateUserLogin(){
        controller.executeMenu(6, 0, "","1234-5678","CrazyPassword");
        assertEquals(controller.getUserLogged(),users.get(0));
    }

    @Test
    public void shouldNotValidateUserLoginWhenPasswordIsWrong(){
        controller.executeMenu(6, 0, "","1234-5678","WrongPassword");
        assertEquals(controller.getUserLogged(),null);
    }


    /*
    --------- LOGOUT ---------
    */

    @Test
    public void shouldLogoutUser(){
        controller.executeMenu(4, 0, null, null,null);
        assertEquals(controller.getUserLogged(),null);
    }


    /*
    --------- USER INFORMATION ---------
    */

    @Test
    public void shouldShowUserInformation(){
        controller.executeMenu(3,0,null,null,null);
        assertEquals("Name: Nadja Sarsur, E-mail: nsarsur@thoughtworks.com, " +
                "Phone number: 9999-9999\n\n\n", answer.toString());
    }

    /*
    --------- LIST ---------
    */

    //BOOK
    @Test
    public void shouldShowAllBooksWhenMenuOptionListBooksIsSelected() {
        controller.executeMenu(0, 0, "","","");
        assertEquals("\nHarry Potter, Rowling, 2000\n" +
                "O alquimista, Paulo Coelho, 1988\n\n", answer.toString());
    }

    //MOVIE
    @Test
    public void shouldShowAllMoviesWhenMenuOptionListMoviesIsSelected() {
        controller.executeMenu(0, 1, "", "","");
        assertEquals("\nPassengers, Morten Tyldum, 2016, 0\n" +
                "Titanic, James Cameron, 1997, 0\n\n", answer.toString());
    }

    /*
    --------- CHECKOUT ---------
    */

    //SUCCESSFUL BOOK CHECKOUT
    @Test
    public void shouldCheckoutBookAndVerifyItIsNotListed(){
        controller.executeMenu(1,0,books.get(0).getTitle(),null,null); //checkout
        answer.reset();
        controller.executeMenu(0, 0, "","", ""); //list
        assertEquals("\nO alquimista, Paulo Coelho, 1988\n\n",answer.toString());
    }

    //SUCCESSFUL MOVIE CHECKOUT
    @Test
    public void shouldCheckoutMovieAndVerifyItIsNotListed(){
        controller.executeMenu(1 ,1,movies.get(0).getTitle(),null,null); //checkout
        answer.reset();
        controller.executeMenu(0, 1, null, null,null); //list
        assertEquals("\nTitanic, James Cameron, 1997, 0\n\n",answer.toString());
    }

    //SUCCESSFUL BOOK CHECKOUT
    @Test
    public void shouldCheckoutBookAndVerifyParameterCheckoutForBook(){
        controller.executeMenu(1 ,0,books.get(0).getTitle(),null,null); //checkout
        assertTrue(books.get(0).isCheckout());
    }

    //SUCCESSFUL MOVIE CHECKOUT
    @Test
    public void shouldCheckoutMovieAndVerifyParameterCheckoutForMovie(){
        controller.executeMenu(1 ,1,movies.get(0).getTitle(),null,null); //checkout
        assertTrue(movies.get(0).isCheckout());
    }

    //UNSUCCESSFUL BOOK CHECKOUT
    @Test
    public void shouldShowUnsuccessfulMessageWhenBookIsAlreadyCheckedOut(){
        controller.executeMenu(1 ,0,books.get(0).getTitle(),null,null); //checkout
        answer.reset();
        controller.executeMenu(1 ,0,books.get(0).getTitle(),null,null); //checkout
        assertEquals("That item is not available.\n\n", answer.toString());
    }

    //UNSUCCESSFUL MOVIE CHECKOUT
    @Test
    public void shouldShowUnsuccessfulMessageWhenMovieIsAlreadyCheckedOut(){
        controller.executeMenu(1 ,1,movies.get(0).getTitle(),null,null); //checkout
        answer.reset();
        controller.executeMenu(1 ,1,movies.get(0).getTitle(),null,null); //checkout
        assertEquals("That item is not available.\n\n", answer.toString());
    }

    //UNSUCCESSFUL BOOK CHECKOUT
    @Test
    public void shouldShowUnsuccessfulMessageWhenBookTitleIsIncorrect(){
        controller.executeMenu(1 ,0,"INCORRECT TITLE",null,null); //checkout
        assertEquals("That item is not available.\n\n", answer.toString());
    }

    //UNSUCCESSFUL BOOK CHECKOUT
    @Test
    public void shouldShowUnsuccessfulMessageWhenMovieTitleIsIncorrect(){
        controller.executeMenu(1 ,1,"INCORRECT TITLE","1234-5678","CrazyPassword"); //checkout
        assertEquals("That item is not available.\n\n", answer.toString());
    }
    /*

    /*
    --------- RETURN ---------
    */

    //SUCCESSFUL BOOK RETURN
    @Test
    public void shouldShowReturnedBookOnTheListBook() {
        controller.executeMenu(1, 0, books.get(0).getTitle(),null,null); //checkout book
        controller.executeMenu(2, 0, books.get(0).getTitle(),null,null); //return book
        answer.reset();
        controller.executeMenu(0, 0, "","",""); //list books
        assertEquals("\nHarry Potter, Rowling, 2000\n" +
                "O alquimista, Paulo Coelho, 1988\n\n", answer.toString());
    }

    //SUCCESSFUL MOVIE RETURN
    @Test
    public void shouldShowReturnedMovieOnTheListMovie() {
        controller.executeMenu(1, 1, movies.get(0).getTitle(),null,null); //checkout movie
        controller.executeMenu(2, 1,movies.get(0).getTitle(),null,null); //return movie
        answer.reset();
        controller.executeMenu(0, 1, "","",""); //list movies
        assertEquals("\nPassengers, Morten Tyldum, 2016, 0\n" +
                "Titanic, James Cameron, 1997, 0\n\n", answer.toString());
    }

    //SUCCESSFUL BOOK RETURN
    @Test
    public void shouldReturnBookAndVerifyParameterCheckoutForMovie(){
        controller.executeMenu(1, 0, books.get(0).getTitle(),null,null); //checkout movie
        controller.executeMenu(2, 0, books.get(0).getTitle(),null,null); //return movie
        assertFalse(movies.get(0).isCheckout());
    }

    //SUCCESSFUL MOVIE RETURN
    @Test
    public void shouldReturnMovieAndVerifyParameterCheckoutForMovie(){
        controller.executeMenu(1, 1, movies.get(0).getTitle(),null,null); //checkout book
        controller.executeMenu(2, 1, movies.get(0).getTitle(),null,null); //return book
        assertFalse(movies.get(0).isCheckout());
    }

    //UNSUCCESSFUL BOOK RETURN
    @Test
    public void shouldShowUnsuccessfulMessageWhenBookIsNotCheckedOut(){
        controller.executeMenu(2 ,0,books.get(0).getTitle(),null,null); //return
        assertEquals("That is not a valid item to return.\n\n", answer.toString());
    }

    //UNSUCCESSFUL MOVIE RETURN
    @Test
    public void shouldShowUnsuccessfulMessageWhenMovieIsNotCheckedOut(){
        controller.executeMenu(2 ,1, movies.get(0).getTitle(),null,null); //return
        assertEquals("That is not a valid item to return.\n\n", answer.toString());
    }

    //UNSUCCESSFUL BOOK RETURN
    @Test
    public void shouldShowUnsuccessfulReturnMessageWhenBookTitleIsIncorrect(){
        controller.executeMenu(2 ,0, "INCORRECT TITLE",null,null); //return
        assertEquals("That is not a valid item to return.\n\n", answer.toString());
    }

    //UNSUCCESSFUL MOVIE RETURN
    @Test
    public void shouldShowUnsuccessfulReturnMessageWhenMovieTitleIsIncorrect(){
        controller.executeMenu(2 ,1, "INCORRECT TITLE",null,null);
        assertEquals("That is not a valid item to return.\n\n", answer.toString());
    }


    /*
    ##################### TESTS TO VERIFY METHODS #####################
    */


    //INVALID MENU OPTION
    @Test
    public void shouldShowUnsuccessfulMessageWhenEntryOptionIsNotValid(){
        int option = menu.getMenuOptionIndex("Invalid Option");
        controller.executeMenu(option,0,"", null,null);
        assertEquals(("Select a valid option!\n\n"), answer.toString());
    }

    //INVALID MENU ITEM
    @Test
    public void shouldShowUnsuccessfulMessageWhenEntryItemIsNotValid(){
        int item = menu.getMenuItemIndex("CD");
        controller.executeMenu(1,item,"",null,null);
        assertEquals(("Select a valid item!\n\n"), answer.toString());
    }

    //RETURN BOOKS
    @Test
    public void methodGetMenuItemShouldReturnBookList(){
        assertEquals(books, controller.getMenuItem(0));
    }

    //RETURN MOVIES
    @Test
    public void methodGetMenuItemShouldReturnMovieList(){
        assertEquals(movies, controller.getMenuItem(1));
    }

}
