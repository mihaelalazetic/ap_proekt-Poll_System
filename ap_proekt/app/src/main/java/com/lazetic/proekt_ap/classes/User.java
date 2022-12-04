package com.lazetic.proekt_ap.classes;

import androidx.annotation.NonNull;

public class User {
    private String email;
    private String password;
    // TODO location


    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
