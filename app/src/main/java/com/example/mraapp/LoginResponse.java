package com.example.mraapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    /*
    {
        "ResultCode": 1,
        "Remark": "Successful",
        "Token": {
            "Name": "kondwa@gmail.com",
            "Value": "521227e2-3def-4940-9a9b-bb817078aec6"
        },
        "Authenticated": true,
        "UserDetails": {
            "Username": "kondwa@gmail.com",
            "Password": "password000133",
            "FirstName": "Kondwani",
            "LastName": "Hara",
            "email": "kondwa@gmail.com"
        }
    }
     */

    public LoginResponse(int resultCode, String remark, Token token, boolean authenticated, User userDetails) {
        this.resultCode = resultCode;
        this.remark = remark;
        this.token = token;
        this.authenticated = authenticated;
        this.userDetails = userDetails;
    }

    @SerializedName("ResultCode")
    @Expose
    private int resultCode;
    @SerializedName("Remark")
    @Expose
    private String remark;
    @SerializedName("Token")
    @Expose
    private Token token;
    @SerializedName("Authenticated")
    @Expose
    private boolean authenticated;
    @SerializedName("UserDetails")
    @Expose
    private User userDetails;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public User getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(User userDetails) {
        this.userDetails = userDetails;
    }
}
