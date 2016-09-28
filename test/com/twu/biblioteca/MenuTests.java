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
        assertEquals(3, menu.getMenuOptionIndex("QUIT"));
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
        assertFalse(menu.validadeOption(4));
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
        assertEquals("QUIT", menu.getMenuOptionString(3));
    }


}

