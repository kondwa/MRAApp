package com.example.mraapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Token {
    /*
    {
        "Name": "kondwa@gmail.com",
        "Value": "521227e2-3def-4940-9a9b-bb817078aec6"
    }
     */

    public Token(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Value")
    @Expose
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
