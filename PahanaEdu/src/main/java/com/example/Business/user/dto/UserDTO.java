package com.example.Business.user.dto;

public class UserDTO {
    private String username;
    private String password;
    private String userType;

    // Constructor for login credentials
    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserDTO() {

    }

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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}