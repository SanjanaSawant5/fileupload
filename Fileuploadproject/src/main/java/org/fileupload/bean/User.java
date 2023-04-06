package org.fileupload.bean;

public class User {
    private String username;
    private String password;

    // Define a constructor that takes two String parameters
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Define getters and setters for username and password
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
