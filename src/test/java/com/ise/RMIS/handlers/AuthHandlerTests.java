package com.ise.RMIS.handlers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class AuthHandlerTests {

    @Test
    public void testAuthHandlerWithValidCredentials() {
        String username = "jdoe";
        String password = "pword123";

        try {
            AuthHandler authHandler = new AuthHandler(username, password);
            assertNotNull(authHandler); // Authentication successful, AuthHandler instance should not be null
        } catch (IllegalArgumentException e) {
            fail("Authentication failed: " + e.getMessage());
        }
    }

    @Test
    public void testAuthHandlerWithInvalidUsername() {
        String username = "invalid";
        String password = "pword123";

        assertThrows(IllegalArgumentException.class, () -> {
            new AuthHandler(username, password);
        });
    }

    @Test
    public void testAuthHandlerWithInvalidPassword() {
        String username = "jdoe";
        String password = "wrong_password";

        assertThrows(IllegalArgumentException.class, () -> {
            new AuthHandler(username, password);
        });
    }
}
