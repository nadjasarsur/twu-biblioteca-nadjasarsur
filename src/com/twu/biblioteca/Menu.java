


package com.twu.biblioteca;

import java.util.Hashtable;
import java.util.List;

/**
 * Created by nsarsur on 9/27/16.
 */
public class Menu {
    private final Hashtable<Integer,String> menuOptions = new Hashtable<Integer,String>();
    private final Hashtable<Integer,String> menuItems = new Hashtable<Integer,String>();

    public Menu() {
        menuOptions.put(0,"LIST");
        menuOptions.put(1,"CHECKOUT");
        menuOptions.put(2,"RETURN");
        menuOptions.put(3,"QUIT");

        menuItems.put(0,"BOOK");
        menuItems.put(1,"MOVIE");

    }

    public void show() {
        System.out.println("**** MAIN MENU ****");
        System.out.println("\t(Options)");
        for (int i=0; i<menuOptions.size(); i++) {
            System.out.println(menuOptions.get(i));
        }
        System.out.println("\t(Items)");
        for (int i=0; i<menuItems.size(); i++) {
            System.out.println(menuItems.get(i));
        }
    }

    public String getMenuOptionString(int option) {
        return menuOptions.get(option);
    }

    public String getMenuItemsString(int item) {
        return menuItems.get(item);
    }

    public boolean validadeOption(int option){
        if(menuOptions.containsKey(option)) {
            return true;
        }
        else return false;
    }

    public boolean validadeItem(int item){
        if(menuOptions.containsKey(item)) {
            return true;
        }
        else return false;
    }

    public int getMenuOptionIndex(String option) {
        for (int i=0; i< menuOptions.size(); i++){
            if(option.toUpperCase().equals(menuOptions.get(i).toUpperCase())) {
                return i;
            }
        }
        return -1;
    }

    public int getMenuItemIndex(String item) {
        for (int i=0; i< menuItems.size(); i++){
            if(item.toUpperCase().equals(menuItems.get(i).toUpperCase())) {
                return i;
            }
        }
        return -1;
    }



}
