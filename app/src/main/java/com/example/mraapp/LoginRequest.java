package com.example.mraapp;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    /*
    {
        "Email": "candidate@gmail.com",
        "Password": "password000122"
     }
     */

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @SerializedName("Email")
    private String email;
    @SerializedName("Password")
    private String password;

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
