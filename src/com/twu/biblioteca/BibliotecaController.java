package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nsarsur on 9/26/16.
 */

public class BibliotecaController {


    private List<Movie> movieList;
    private List<Book> bookList;
    private BibliotecaApp biblio;
    private Menu menu;

    public BibliotecaController(List<Book> bookList, List<Movie> movieList) {
        this.bookList = bookList;
        this.movieList = movieList;
        menu = new Menu();
        biblio = new BibliotecaApp();

    }

    public String readFromKeyboard(String toPrint){
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);
        String entry = null;
        System.out.print(toPrint);
        try {
            entry = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entry;
    }

    public int readMenuOption(){
        String menuOption = readFromKeyboard("\nSELECT OPTION: ");
        int option = menu.getMenuOptionIndex(menuOption);
        return option;
    }


    public int readMenuItem(){
        String menuItem = readFromKeyboard("SELECT ITEM: ");
        int item = menu.getMenuItemIndex(menuItem);
        return item;

    }

    public String readTitle(){
        String title = readFromKeyboard("TITLE: ");
        return title;
    }

    public void readEntries() {
        int option = readMenuOption();
        if(menu.validadeOption(option) &&
                !menu.getMenuOptionString(option).toUpperCase().equals("QUIT")) {
            int item = readMenuItem();
            if(menu.validadeItem(item) &&
                    !menu.getMenuOptionString(option).toUpperCase().equals("LIST")) {
                String title = readTitle();
                executeMenu(option, item, title);
            }
            else executeMenu(option, item, null);
        }
        else executeMenu(option, -1, null);
    }

    public List<ItemBiblioteca> getMenuItem(int item) {
        switch (item) {
            case -1:    //Invalid option
                System.out.println("Select a valid item!\n");
                return null;
            case 0:     //Book
                return new ArrayList<ItemBiblioteca>(bookList);
            case 1:     //Movie
                return new ArrayList<ItemBiblioteca>(movieList);
        }
        return null;
    }


    public void executeMenu(int option, int item, String title) {
        List<ItemBiblioteca> items;
        switch (option){
            case -1:    //Invalid option
                System.out.println("Select a valid option!\n");
                break;
            case 0:     //List
                items = getMenuItem(item);
                if(items != null ) {
                    System.out.println();
                    biblio.listItems(items);
                    System.out.print("\n\n");
                }
                break;
            case 1:     //Checkout
                items = getMenuItem(item);
                if(items != null)
                    biblio.setCheckoutItemByName(items,title);
                break;
            case 2:     //Return
                items = getMenuItem(item);
                if(items != null)
                    biblio.returnItemByName(items,title);
                break;
            case 3:     //Quit
                biblio.setFlag_quit(true);
                System.out.println("Bye User!");
                break;
            default:    break;
        }

    }

    public void controlBibliotecaApp() {

        while(!biblio.getFlag_quit())  {
            menu.show();
            readEntries();
        }

    }
}
