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

    public <T extends ItemBiblioteca> boolean setCheckoutItemByName(List<T> items, String title, Account user) {
        int index = findItemOnLibraryByName(items, title);

        if(index >=0 && !items.get(index).isCheckout()) {
            items.get(index).setCheckout(true);
            items.get(index).setUser(user);
            System.out.println("Thank you! Enjoy the item.");
            System.out.println("Book checked out by " + items.get(index).getUser().getName()+"\n");
            return true;
        }
        else {
            System.out.println("That item is not available.\n");
            return false;
        }
    }

    public <T extends ItemBiblioteca> boolean returnItemByName(List<T> items, String title, Account user) {
        int index = findItemOnLibraryByName(items, title);

        if(index >=0 && items.get(index).isCheckout()) {
            if(items.get(index).getUser().equals(user)) {
                items.get(index).setCheckout(false);
                System.out.println("Thank you for returning the item.\n");
                return true;
            }
            else {
                System.out.println("This item was checked out by " + items.get(index).getUser().getName()+"\n\n");
                return false;
            }
        }
        else {
            System.out.println("That is not a valid item to return.\n");
            return false;
        }
    }

    public void setFlag_quit(boolean flag_quit) {
        this.flag_quit = flag_quit;
    }

    public <T extends ItemBiblioteca>  T getItemByName(List<T> items,String title) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getTitle().toUpperCase().equals(title.toUpperCase())) {
                return items.get(i);
            }
        }
        return null;
    }
}
