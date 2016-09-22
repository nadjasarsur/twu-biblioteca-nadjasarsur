package com.twu.biblioteca;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nsarsur on 9/21/16.
 */
import java.io.*;

public class main {

    public static void main(String [] args) {

        List<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book("Harry Potter","Rowling",2000));
        bookList.add(new Book("O alquimista","Paulo Coelho",1988));
        bookList.add(new Book("TDD by example","Marcel Pepescu",2011));

        BibliotecaApp biblio = new BibliotecaApp();

        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);
        String option;

        while(!biblio.getFlag_quit()) try {
            biblio.menu.show();
            option = br.readLine();
            biblio.getMenuOptions(option,bookList);

            } catch (IOException e) {
                e.printStackTrace();
        }

    }
}
