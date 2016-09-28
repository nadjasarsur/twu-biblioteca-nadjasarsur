package com.twu.biblioteca;

import java.util.List;

/**
 * Created by nsarsur on 9/28/16.
 */
public class Login {
    private List<Account> users;

    public Login(List<Account> users) {
        this.users = users;
    }

    public Account validadeUserLogin(String login, String password){
        for (int i=0; i < users.size(); i++){
            if(login.equals(users.get(i).getLogin()) &&
                    password.equals(users.get(i).getPassword())) {
                return users.get(i);
            }
        }
        return null;
    }
}
