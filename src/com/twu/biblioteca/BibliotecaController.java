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
    private Login login;
    private BibliotecaApp biblio;
    private Menu menu;
    private boolean userIsLogged;
    private Account userLogged;


    public BibliotecaController(List<Book> bookList, List<Movie> movieList, Login login) {
        this.bookList = bookList;
        this.movieList = movieList;
        biblio = new BibliotecaApp();
        this.login = login;
        menu = new Menu();
        userIsLogged = false;
        userLogged = null;

    }

    public void setUserLogged(Account userLogged) {
        this.userLogged = userLogged;
    }

    public Account getUserLogged() {
        return userLogged;
    }

    private String readFromKeyboard(String toPrint){
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

    private int readMenuOption(){
        String menuOption = readFromKeyboard("\nSELECT OPTION: ");
        int option = menu.getMenuOptionIndex(menuOption);
        return option;
    }


    private int readMenuItem(){
        String menuItem = readFromKeyboard("SELECT ITEM: ");
        int item = menu.getMenuItemIndex(menuItem);
        return item;

    }

    private String readTitle(){
        String title = readFromKeyboard("TITLE: ");
        return title;
    }

    private String readUserLogin(){
        String title = readFromKeyboard("USER: ");
        return title;
    }
    private String readUserPassword(){
        String title = readFromKeyboard("PASSWORD: ");
        return title;
    }

    private void readEntries() {
        int option = readMenuOption();
        //Invalid option!
        if (!userIsLogged && isAValidOptionOnlyForUserLogged(option)) {
            executeMenu(-1, -1, null, null, null);
        }
        else {
            if (isAValidOptionAndIsNotToQuit(option)) {
                //Login
                if(isLoginOption(option)) {
                    String userLogin = readUserLogin();
                    String userPassword = readUserPassword();
                    executeMenu(option, -1, null, userLogin, userPassword);
                }
                else{
                    //Logout or User Information
                    if (isLogoutOptionOrUserInformationOption(option)) {
                        executeMenu(option, -1, null, null, null);
                    }
                    else {
                        //List, Checkout or Return
                        int item = readMenuItem();
                        if (menu.validadeItem(item) && !isListOption(option)) {
                            String title = readTitle();
                            executeMenu(option, item, title, null, null);
                        } else executeMenu(option, item, null, null, null);
                    }
                }
            }
            //Quit or invalid options
            else { executeMenu(option, -1, null, null, null); }
        }

    }

    private boolean isListOption(int option) {
        return menu.getMenuOptionString(option).toUpperCase().equals("LIST");
    }

    private boolean isLoginOption(int option) {
        return menu.getMenuOptionString(option).toUpperCase().equals("LOGIN");
    }

    private boolean isAValidOptionAndIsNotToQuit(int option) {
        return menu.validadeOption(option) &&
                !menu.getMenuOptionString(option).toUpperCase().equals("QUIT");
    }

    private boolean isLogoutOptionOrUserInformationOption(int option) {
        return menu.getMenuOptionString(option).toUpperCase().equals("USER INFORMATION") ||
                menu.getMenuOptionString(option).toUpperCase().equals("LOGOUT");
    }

    private boolean isAValidOptionOnlyForUserLogged(int option) {
        return menu.getMenuOptionString(option).toUpperCase().equals("CHECKOUT") ||
                menu.getMenuOptionString(option).toUpperCase().equals("RETURN") ||
                menu.getMenuOptionString(option).toUpperCase().equals("LOGOUT") ||
                menu.getMenuOptionString(option).toUpperCase().equals("USER INFORMATION");
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


    public void executeMenu(int option, int item, String title, String userLogin, String userPassword) {
        List<ItemBiblioteca> items;
        switch (option){
            case -1:    //Invalid option
                System.out.println("Select a valid option!\n");
                break;
            case 0:     //List
                executeMenuList(item);
                break;
            case 1:     //Checkout
                executeMenuCheckout(item, title);
                break;
            case 2:     //Return
                executeMenuReturn(item, title);
                break;
            case 3:     //User Information
                userLogged.showUserInformation();
                System.out.print("\n");
                break;
            case 4:     //Logout
                userIsLogged = false;
                userLogged = null;
                System.out.print("\n");
                break;
            case 5:     //Quit
                biblio.setFlag_quit(true);
                System.out.println("Bye User!");
                break;
            case 6:     //Login
                userLogin(userLogin,userPassword);
                System.out.print("\n");
                break;
            default:    break;
        }

    }

    private void executeMenuReturn(int item, String title) {
        List<ItemBiblioteca> items;
        items = getMenuItem(item);
        if(items != null) {
            boolean returnWasValidated = biblio.returnItemByName(items, title, userLogged);
            if(returnWasValidated) {
                userLogged.removeItem(biblio.getItemByName(items, title));
            }
        }
    }

    private void executeMenuCheckout(int item, String title) {
        List<ItemBiblioteca> items;
        items = getMenuItem(item);
        if (items != null) {
            boolean checkoutWasValidated = biblio.setCheckoutItemByName(items, title, userLogged);
            if (checkoutWasValidated){
                userLogged.addItem(biblio.getItemByName(items,title));
            }
        }
    }

    private void executeMenuList(int item) {
        List<ItemBiblioteca> items;
        items = getMenuItem(item);
        if(items != null ) {
            System.out.println();
            biblio.listItems(items);
            System.out.print("\n\n");
        }
    }

    private void userLogin(String userLogin, String userPassword) {
        userLogged = login.validadeUserLogin(userLogin,userPassword);
        if (userLogged != null){
            userIsLogged = true;
        }
        else {
            System.out.println("Invalid user!\n");
        }
    }

    public void controlBibliotecaApp() {
        biblio.getWelcomeMessage();
        while(!biblio.getFlag_quit())  {
            menu.show(userIsLogged);
            readEntries();
        }

    }
}
