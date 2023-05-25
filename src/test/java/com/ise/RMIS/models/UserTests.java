package com.ise.RMIS.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UserTests {

    @Test
    public void testGetUsername() {
        String username = "john_doe";
        String password = "password123";
        User user = new User(username, password);

        assertEquals(username, user.getUsername());
    }

    @Test
    public void testIsPasswordCorrect() {
        String username = "john_doe";
        String password = "password123";
        User user = new User(username, password);

        assertTrue(user.isPasswordCorrect(password));
        assertFalse(user.isPasswordCorrect("wrong_password"));
    }

    @Test
    public void testIsUsernameCorrect() {
        String username = "john_doe";
        String password = "password123";
        User user = new User(username, password);

        assertTrue(user.isUsernameCorrect(username));
        assertFalse(user.isUsernameCorrect("wrong_username"));
    }

    @Test
    public void testToString() {
        String username = "john_doe";
        String password = "password123";
        User user = new User(username, password);

        String expected = username + "," + password;
        assertEquals(expected, user.toString());
    }
}
