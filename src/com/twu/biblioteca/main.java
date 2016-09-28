package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by nsarsur on 9/21/16.
 */

public class main {

    public static void main(String [] args) {

        List<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book("Harry Potter","Rowling",2000));
        bookList.add(new Book("O alquimista","Paulo Coelho",1988));
        bookList.add(new Book("TDD by example","Marcel Pepescu",2011));


        List<Movie> movieList = new ArrayList<Movie>();
        movieList.add(new Movie("Passengers",2016,"Morten Tyldum"));
        movieList.add(new Movie("Titanic",1997,"James Cameron"));
        movieList.add(new Movie("Gladiador",2000,"Ridley Scott"));


        BibliotecaController controller = new BibliotecaController(bookList,movieList);
        controller.controlBibliotecaApp();

    }
}
