package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nsarsur on 9/26/16.
 */
public class MovieTests {
    BibliotecaApp biblio;
    List<Movie> movies;
    ByteArrayOutputStream answer = new ByteArrayOutputStream();

    @Before
    public void setUp(){
        System.setOut(new PrintStream(answer));
        biblio = new BibliotecaApp();
        movies = new ArrayList<Movie>();
        movies.add(new Movie("Passengers",2016,"Morten Tyldum"));
        movies.add(new Movie("Titanic",1997,"James Cameron"));
        movies.add(new Movie("Gladiador",2000,"Ridley Scott"));
    }

    @After
    public void tearDown() {
            System.setOut(null);
    }




}



/** List Movies - As a customer, I would like to see a list of available movies, so that I can browse for
        a movie that I might check-out. Movies have a name, year, director and movie rating (from 1-10 or unrated).
 */