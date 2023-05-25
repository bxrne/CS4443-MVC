package com.ise.RMIS.handlers;

import com.ise.RMIS.models.User;

public class AuthHandler {
    /*
     * For the sake of simplicity, we will be using a single user for authentication
     * That is prepopulated statically in this handler
     */

    private User user = new User("jdoe", "pword123");

    public AuthHandler(String username, String password) { // if no IllegalArgumentException is thrown, then the user is
        // authenticated

        if (!user.isUsernameCorrect(username)) {
            throw new IllegalArgumentException("Invalid username");
        }

        if (!user.isPasswordCorrect(password)) {
            throw new IllegalArgumentException("Invalid password");
        }

    }

}
