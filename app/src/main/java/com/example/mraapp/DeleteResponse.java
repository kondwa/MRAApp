package com.example.mraapp;

import com.google.gson.annotations.SerializedName;

public class DeleteResponse {
    /*
    {
        "ResultCode": 1,
        "Remark": "Taxpayer deleted successfully!",
        "data": "20203030"
    }
     */

    public DeleteResponse(int resultCode, String remark, String data) {
        this.resultCode = resultCode;
        this.remark = remark;
        this.data = data;
    }

    @SerializedName("ResultCode")
    private int resultCode;
    @SerializedName("Remark")
    private String remark;
    @SerializedName("data")
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
