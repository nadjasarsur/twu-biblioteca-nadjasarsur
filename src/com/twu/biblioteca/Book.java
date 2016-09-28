package com.twu.biblioteca;

/**
 * Created by nsarsur on 9/21/16.
 */
public class Book extends ItemBiblioteca{

    private String author;

    public Book(String title, String author, int year) {

        super(title,year);
        this.author = author;
        setCheckout(false);
    }

    public String getAuthor() {
        return author;
    }

    public String details() {
        return (getTitle()+", "+getAuthor()+", "+Integer.toString((getYear())));
    }

}
