package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.*;

/**
 * Created by nsarsur on 9/28/16.
 */
public class AccountTests {

    private ByteArrayOutputStream answer;
    private Account user;
    private Book book;
    private Movie movie;


    @Before
    public void setUp() {
        answer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(answer));

        user = new Account("1234-5678", "CrazyPassword", "Nadja Sarsur", "nsarsur@thoughtworks.com", "9999-9999");
        movie = new Movie("Titanic",1997,"James Cameron");
        book = new Book("Harry Potter","Rowling",2000);

    }
    @After
    public void tearDown() {
        System.setOut(null);
    }

    /*
    ##################### TESTS TO VERIFY METHODS #####################
    */

    @Test
    public void shouldShowUserInformation(){
        user.showUserInformation();
        assertEquals("Name: Nadja Sarsur, E-mail: nsarsur@thoughtworks.com, " +
                "Phone number: 9999-9999\n\n", answer.toString());
    }

    @Test
    public void shouldValidadeTheOperationRemoveItemOfAnUser(){
        user.addItem(movie);
        assertTrue(user.removeItem(movie));
    }

    @Test
    public void shouldNotValidadeTheOperationRemoveItemOfAnUser(){
        user.addItem(movie);
        assertFalse(user.removeItem(book));
    }

}
