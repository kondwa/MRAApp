package com.example.mraapp;

import com.google.gson.annotations.SerializedName;

public class LogoutResponse {
    /*
    {
        "ResultCode": 1,
        "Remark": "Log out Successful",
        "data": null
    }
     */

    public LogoutResponse(int resultCode, String remark, Object data) {
        this.resultCode = resultCode;
        this.remark = remark;
        this.data = data;
    }

    @SerializedName("ResultCode")
    private int resultCode;
    @SerializedName("Remark")
    private String remark;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @SerializedName("data")
    private Object data;
}
