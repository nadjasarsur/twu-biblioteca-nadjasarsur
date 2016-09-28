package com.twu.biblioteca;

import java.util.List;


public class BibliotecaApp {


    private boolean flag_quit;

    public BibliotecaApp() {
        flag_quit = false;
    }

    public boolean getFlag_quit() {
        return flag_quit;
    }

    public void getWelcomeMessage() {
        System.out.print("WELCOME!!!\n");
    }


    public <T extends ItemBiblioteca> void listItems(List<T> items) {
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


    public <T extends ItemBiblioteca> int findItemOnLibraryByName(List<T> items, String title) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getTitle().toUpperCase().equals(title.toUpperCase())) {
                return i;
            }
        }
        return -1;
    }

    public <T extends ItemBiblioteca> void setCheckoutItemByName(List<T> items, String title) {
        int index = findItemOnLibraryByName(items, title);

        if(index >=0 && !items.get(index).isCheckout()) {
            items.get(index).setCheckout(true);
            System.out.println("Thank you! Enjoy the item.\n");
        }
        else {
            System.out.println("That item is not available.\n");
        }
    }

    public <T extends ItemBiblioteca> void returnItemByName(List<T> items, String title) {
        int index = findItemOnLibraryByName(items, title);

        if(index >=0 && items.get(index).isCheckout()) {
            items.get(index).setCheckout(false);
            System.out.println("Thank you for returning the item.\n");
        }
        else {
            System.out.println("That is not a valid item to return.\n");
        }
    }

    public void setFlag_quit(boolean flag_quit) {
        this.flag_quit = flag_quit;
    }
}
