package com.example.moodtrackerapp;

public class User {

    private String email;
    private String fullName;
    private String username;
    private String password;

    public User() {}

    public User(String email, String password) {
        this.password = password;
        this.email = email;
    }

    public User(String fullName, String username, String email) {
        this.email = email;
        this.fullName = fullName;
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
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
