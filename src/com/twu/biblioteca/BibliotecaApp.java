package com.twu.biblioteca;

import java.util.List;

public class BibliotecaApp {

    public static void main(String[] args) {
        System.out.println("Hello, world!");
    }

    public static String getWelcomeMessage() {
        return "Welcome";
    }

    public static String getAllLibraryBooks(List<String> allLibraryBooks) {
        if(allLibraryBooks == null || allLibraryBooks.isEmpty()) {
            return "There are no books!";
        }
        else {
            StringBuilder builder = getStringBuilder(allLibraryBooks);
            return builder.toString();
        }
    }

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


}
