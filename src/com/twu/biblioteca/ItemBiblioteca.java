package com.twu.biblioteca;

/**
 * Created by nsarsur on 9/26/16.
 */
public abstract class ItemBiblioteca {
    private String title;
    private int year;
    private boolean checkout;

    public ItemBiblioteca(String title, int year) {
        this.title = title;
        this.year = year;
        this.checkout = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isCheckout() {
        return checkout;
    }

    public void setCheckout(boolean checkout) {
        this.checkout = checkout;
    }

    public abstract String details();
}
