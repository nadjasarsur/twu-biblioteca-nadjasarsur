package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by nsarsur on 9/28/16.
 */
public class MenuTests {

    private Menu menu = new Menu();


    @Test
    public void shouldVerifyMenuOptions(){
        assertEquals(0, menu.getMenuOptionIndex("LIST"));
        assertEquals(1, menu.getMenuOptionIndex("CHECKOUT"));
        assertEquals(2, menu.getMenuOptionIndex("RETURN"));
        assertEquals(3, menu.getMenuOptionIndex("USER INFORMATION"));
        assertEquals(4, menu.getMenuOptionIndex("LOGOUT"));
        assertEquals(5, menu.getMenuOptionIndex("QUIT"));
        assertEquals(6, menu.getMenuOptionIndex("LOGIN"));
    }

    @Test
    public void shouldVerifyMenuItems(){
        assertEquals(0, menu.getMenuItemIndex("BOOK"));
        assertEquals(1, menu.getMenuItemIndex("MOVIE"));
    }

    @Test
    public void shouldValidateMenuOptions() {
        assertTrue(menu.validadeOption(0));
        assertTrue(menu.validadeOption(1));
        assertTrue(menu.validadeOption(2));
        assertTrue(menu.validadeOption(3));
        assertTrue(menu.validadeOption(4));
        assertTrue(menu.validadeOption(5));
        assertTrue(menu.validadeOption(6));
        assertFalse(menu.validadeOption(7));
    }

    @Test
    public void shouldValidateMenuItems(){
        assertTrue(menu.validadeItem(0));
        assertTrue(menu.validadeItem(1));
        assertFalse(menu.validadeItem(2));
    }

    @Test
    public void shouldVerifyMenuOptionString(){
        assertEquals("LIST", menu.getMenuOptionString(0));
        assertEquals("CHECKOUT", menu.getMenuOptionString(1));
        assertEquals("RETURN", menu.getMenuOptionString(2));
        assertEquals("USER INFORMATION", menu.getMenuOptionString(3));
        assertEquals("LOGOUT", menu.getMenuOptionString(4));
        assertEquals("QUIT", menu.getMenuOptionString(5));
        assertEquals("LOGIN", menu.getMenuOptionString(6));
    }

}

