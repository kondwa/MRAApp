package com.example.mraapp;

import com.google.gson.annotations.SerializedName;

public class DeleteRequest {
    /*
    {
        "TPIN": "20203030"
    }
     */

    public DeleteRequest(String tpin) {
        this.tpin = tpin;
    }

    @SerializedName("TPIN")
    private String tpin;

    public String getTpin() {
        return tpin;
    }

    public void setTpin(String tpin) {
        this.tpin = tpin;
    }
}
