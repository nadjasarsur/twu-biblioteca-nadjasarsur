package com.twu.biblioteca;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ExampleTest {


    @Test
    public void test() {
        assertEquals(1, 1);
    }

    @Test
    public void shouldWelcomeCustomer(){
        assertEquals("Welcome", BibliotecaApp.getWelcomeMessage());
    }

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
        allLibraryBooks.add("");
        assertEquals("Harry Potter, TDD by example", BibliotecaApp.getAllLibraryBooks(allLibraryBooks));
    }

    @Test
    public void shouldAlertCustomerWhenTheBookListIsEmpty(){
        List<String> allLibraryBooks = new ArrayList<>();
        assertEquals("There are no books!", BibliotecaApp.getAllLibraryBooks(allLibraryBooks));
    }

    @Test
    public void shouldAlertCustomerWhenTheBookListIsNull(){
        List<String> allLibraryBooks = null;
        assertEquals("There are no books!", BibliotecaApp.getAllLibraryBooks(allLibraryBooks));
    }
}
