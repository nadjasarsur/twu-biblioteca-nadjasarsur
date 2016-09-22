package com.twu.biblioteca;

/**
 * Created by nsarsur on 9/21/16.
 */
public class Book {

    private String title;
    private String author;
    private int year;
    private boolean checkout;

    public Book(String title, String author, int year) {

        this.title = title;
        this.author = author;
        this.year = year;
        this.checkout = false;
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

    public boolean isCheckout() {
        return checkout;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setCheckout(boolean checkout) {
        this.checkout = checkout;
    }
}
