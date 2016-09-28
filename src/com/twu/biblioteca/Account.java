package com.twu.biblioteca;

import java.util.ArrayList;

/**
 * Created by nsarsur on 9/28/16.
 */
public class Account {
    private String login;
    private String name;
    private String email;
    private String phone;
    private String password;
    private ArrayList<ItemBiblioteca> items;

    public Account(String number, String password, String name, String email, String phone) {
        this.login = number;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.items = new ArrayList<ItemBiblioteca>();
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public <T extends ItemBiblioteca> void addItem(T item){
        this.items.add(item);
    }

    public <T extends ItemBiblioteca> boolean removeItem(T item){
        for(int i=0; i<items.size(); i++){
            if(items.get(i).equals(item)){
                items.remove(i);
                return true;
            }
        }
        return false;
    }

    public void showUserInformation(){
        System.out.println("Name: " + name + ", E-mail: " + email + ", Phone number: " + phone + "\n");
    }

    public String getName() {
        return name;
    }
}
