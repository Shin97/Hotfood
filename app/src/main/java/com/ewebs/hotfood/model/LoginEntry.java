package com.ewebs.hotfood.model;

/**
 * Created by Paul on 2016/12/30.
 */

public class LoginEntry {
    private String user,password;

    public LoginEntry(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
