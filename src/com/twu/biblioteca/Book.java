package com.twu.biblioteca;

/**
 * Created by nsarsur on 9/21/16.
 */
public class Book {

    private String title;
    private String author;
    private int year;

    public Book(String title, String author, int year) {

        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }
}
