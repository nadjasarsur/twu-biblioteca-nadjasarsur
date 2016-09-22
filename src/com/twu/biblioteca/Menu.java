package com.twu.biblioteca;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by nsarsur on 9/21/16.
 */
public class Menu {
    private List<String> menuOptions = new ArrayList<String>();
    ;

    public Menu() {

        menuOptions.add("List Books");
        menuOptions.add("Checkout Book");
        menuOptions.add("Return Book");
        menuOptions.add("Quit");
    }

    public void show() {
        System.out.println("**** MAIN MENU ****");
        for (int i=0; i<menuOptions.size(); i++) {
            System.out.println(menuOptions.get(i));
        }
        System.out.print("\nOPTION: ");
    }

    public List<String> getMenuOptions() {
        return menuOptions;
    }

    public void setMenuOptions(List<String> menuOptions) {
        this.menuOptions = menuOptions;
    }
}
