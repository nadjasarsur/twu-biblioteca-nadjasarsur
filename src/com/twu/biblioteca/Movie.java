package com.twu.biblioteca;

/**
 * Created by nsarsur on 9/26/16.
 */
public class Movie extends ItemBiblioteca {
    private String director;
    private int rating;

    public Movie(String title, int year, String director) {
        super(title, year);
        this.director = director;
        this.rating = 0;
        setCheckout(false);
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String details() {
        return (getTitle()+", "+getDirector()+", "+Integer.toString(getYear())+", "+rating);

    }
}
