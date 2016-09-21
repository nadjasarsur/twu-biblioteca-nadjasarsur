package com.twu.biblioteca;

import java.util.List;

public class BibliotecaApp {

    public static void main(String[] args) {
        System.out.println("Hello, world!");
    }

    public static String getWelcomeMessage() {
        return "Welcome";
    }



    public static String getAllLibraryBooks(List<Book> books) {
        StringBuilder builder = new StringBuilder();
        if(books == null || books.isEmpty()) {
            return "There are no books!";
        }
        else {
            for (int i = 0; i < books.size(); i++) {
                builder.append(getBooksDetails(books.get(i)));
                if(i < books.size()-1 ) {
                    builder.append("\n");
                }
            }
            return builder.toString();
        }
    }


    public static String getBooksDetails(Book book) {
        return (book.getTitle()+", "+book.getAuthor()+", "+Integer.toString((book.getYear())));
    }

/*
    private static StringBuilder getStringBuilder(List<String> allLibraryBooks) {
        StringBuilder builder = new StringBuilder();
        for(int i=0; i<allLibraryBooks.size(); i++) {
            if(i<allLibraryBooks.size()-1)
                builder.append(allLibraryBooks.get(i)+", ");
            else{
                builder.append(allLibraryBooks.get(i));
            }
        }
        return builder;
    }
*/


}
