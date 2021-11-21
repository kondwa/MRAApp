package com.example.mraapp;

import com.google.gson.annotations.SerializedName;

public class LogoutRequest {
    /*
    {
        "Email": "bmangisoni@mra.mw"
    }
     */

    public LogoutRequest(String email) {
        this.email = email;
    }

    @SerializedName("Email")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
