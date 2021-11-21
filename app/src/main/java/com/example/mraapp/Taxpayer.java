package com.example.mraapp;

import com.google.gson.annotations.SerializedName;

public class Taxpayer {
    /*
    {
        "TPIN": "20202021",
        "BusinessCertificateNumber": "MBRS123456",
        "TradingName": "Test Trading Name",
        "BusinessRegistrationDate": "2021/11/02",
        "MobileNumber": "0995926084",
        "Email": "bmangisoni@gmail.com",
        "PhysicalLocation": "Namiwawa",
        "Username": "bmangisoni",
        "Deleted": false,
        "id": 5
     */

    public Taxpayer(int id, String tpin, String businessCertificateNumber, String tradingName, String businessRegistrationDate, String mobileNumber, String email, String physicalLocation, String username, boolean deleted) {
        this.id = id;
        this.tpin = tpin;
        this.businessCertificateNumber = businessCertificateNumber;
        this.tradingName = tradingName;
        this.businessRegistrationDate = businessRegistrationDate;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.physicalLocation = physicalLocation;
        this.username = username;
        this.deleted = deleted;
    }

    @SerializedName("id")
    private int id;
    @SerializedName("TPIN")
    private String tpin;
    @SerializedName("BusinessCertificateNumber")
    private String businessCertificateNumber;
    @SerializedName("TradingName")
    private String tradingName;
    @SerializedName("BusinessRegistrationDate")
    private String businessRegistrationDate;
    @SerializedName("MobileNumber")
    private String mobileNumber;
    @SerializedName("Email")
    private String email;
    @SerializedName("PhysicalLocation")
    private String physicalLocation;
    @SerializedName("Username")
    private String username;
    @SerializedName("Deleted")
    private boolean deleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTpin() {
        return tpin;
    }

    public void setTpin(String tpin) {
        this.tpin = tpin;
    }

    public String getBusinessCertificateNumber() {
        return businessCertificateNumber;
    }

    public void setBusinessCertificateNumber(String businessCertificateNumber) {
        this.businessCertificateNumber = businessCertificateNumber;
    }

    public String getTradingName() {
        return tradingName;
    }

    public void setTradingName(String tradingName) {
        this.tradingName = tradingName;
    }

    public String getBusinessRegistrationDate() {
        return businessRegistrationDate;
    }

    public void setBusinessRegistrationDate(String businessRegistrationDate) {
        this.businessRegistrationDate = businessRegistrationDate;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhysicalLocation() {
        return physicalLocation;
    }

    public void setPhysicalLocation(String physicalLocation) {
        this.physicalLocation = physicalLocation;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
