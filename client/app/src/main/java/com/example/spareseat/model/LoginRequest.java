package com.example.spareseat.model;

public class LoginRequest {
    private String email;
    private String phoneNumber;
    private String password;

    public LoginRequest(String email, String phoneNumber, String password) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
}
