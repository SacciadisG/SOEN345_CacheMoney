package com.example.spareseat.model;

public class UserRegistrationRequest {
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    private String role;

    public UserRegistrationRequest(String name, String email, String phoneNumber, String password, String role) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
    }
}
