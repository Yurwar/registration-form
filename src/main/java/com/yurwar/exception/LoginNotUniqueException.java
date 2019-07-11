package com.yurwar.exception;

public class LoginNotUniqueException extends Exception {
    private String login;

    public LoginNotUniqueException(String login, String message) {
        super(message);
        this.login = login;
    }

    public LoginNotUniqueException(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }
}
