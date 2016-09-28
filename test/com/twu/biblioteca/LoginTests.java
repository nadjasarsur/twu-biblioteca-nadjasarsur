package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by nsarsur on 9/28/16.
 */
public class LoginTests {


    Login login;
    private ArrayList<Account> users;


    @Before
    public void setUp() {
        users = new ArrayList<Account>();
        users.add(new Account("1234-5678", "CrazyPassword", "Nadja Sarsur", "nsarsur@thoughtworks.com", "9999-9999"));
        users.add(new Account("5555-2222","HelloWorld!"," Brad Pitty","bpitty@thoughtworks.com","9999-9999"));

        login = new Login(users);
    }

    @Test
    public void shouldValidateUserLogin(){
        Account account = login.validadeUserLogin("1234-5678", "CrazyPassword");
        assertEquals(account,users.get(0));
    }

    @Test
    public void shouldNotValidateUserLogin(){
        Account account = login.validadeUserLogin("9999-9999", "CrazyPassword");
        assertEquals(account,null);
    }


}
