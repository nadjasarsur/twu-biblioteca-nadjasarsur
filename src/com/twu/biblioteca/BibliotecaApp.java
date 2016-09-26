package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class BibliotecaApp {


    private boolean flag_quit;
    public Menu menu;

    public BibliotecaApp() {
        flag_quit = false;
        menu = new Menu();
    }

    public boolean getFlag_quit() {
        return flag_quit;
    }


    public void getWelcomeMessage() {
        System.out.print("WELCOME!!!\n");
    }


    public <T extends ItemBiblioteca> void getAllLibraryItems(List<T> items) {
        StringBuilder builder = new StringBuilder();
        if(!(items == null) && !items.isEmpty()) {
            for (int i = 0; i < items.size(); i++) {
                if (!items.get(i).isCheckout()) {
                    builder.append((items.get(i).details()));
                    if (i < items.size() - 1) {
                        builder.append("\n");
                    }
                }
            }
            System.out.print(builder.toString());
            return;
        }
        System.out.print("There are no items!");
    }

    public int validateMenuOptions(String menuOption) {
        for (int i=0; i<menu.getMenuOptions().size(); i++){
            if(menuOption.toUpperCase().equals(menu.getMenuOptions().get(i).toUpperCase())) {
                return i;
            }
        }
        return -1;
    }

    private <T extends ItemBiblioteca> int findBookOnLibraryByName(List<T> items, String title) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getTitle().toUpperCase().equals(title.toUpperCase())) {
                return i;
            }
        }
        return -1;
    }

    private String readFromKeyboard(){
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);
        String entry = null;
        System.out.print("Title: ");
        try {
            entry = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entry;
    }

    public <T extends ItemBiblioteca> void getMenuOptions(String menuOption, List<T> items) {
        int option = validateMenuOptions(menuOption);
        switch (option){
            case -1:    //Invalid option
                        System.out.println("Select a valid option!\n");
                        break;
            case 0:     //List Books
                        System.out.println();
                        getAllLibraryItems(items);
                        System.out.print("\n\n");
                        break;
            case 1:     //Checkout Book
                        checkoutBook(items);
                        break;
            case 2:     //Return Book
                        returnBook(items);
                        break;
            case 3:     //Quit
                        flag_quit = true;
                        System.out.println("Bye User!");
                        break;
            default:    break;
        }
    }

    private <T extends ItemBiblioteca> void checkoutBook(List<T> items) {
        String title = readFromKeyboard();
        setCheckoutBookByName(items,title);
    }

    public <T extends ItemBiblioteca> void setCheckoutBookByName(List<T> items, String title) {
        int index = findBookOnLibraryByName(items, title);

        if(index >=0 && !items.get(index).isCheckout()) {
            items.get(index).setCheckout(true);
            System.out.println("Thank you! Enjoy the item.\n");
        }
        else {
            System.out.println("That book is not available.\n");
        }
    }

    private <T extends ItemBiblioteca> void returnBook(List<T> books) {
        String title = readFromKeyboard();
        returnBookByName(books,title);
    }


    public <T extends ItemBiblioteca> void returnBookByName(List<T> items, String title) {
        int index = findBookOnLibraryByName(items, title);

        if(index >=0 && items.get(index).isCheckout()) {
            items.get(index).setCheckout(false);
            System.out.println("Thank you for returning the item.\n");
        }
        else {
            System.out.println("That is not a valid item to return.\n");
        }

    }

}
