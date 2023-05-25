package com.ise.RMIS.models;

public class User {
    private String username;
    private String password;

    public User(String _username, String _password) {
        username = _username;
        password = _password;
    }

    public String getUsername() {
        return username;
    }

    public boolean isUsernameCorrect(String _username) {
        return username.equals(_username);
    }

    public boolean isPasswordCorrect(String _password) {
        return password.equals(_password);
    }

    public String toString() {
        return username + "," + password;
    }

}
