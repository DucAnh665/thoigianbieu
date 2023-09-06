package com.example.lich.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StatusResponse {
    @SerializedName("statusCode")
    @Expose
    private int statusCode;
    @SerializedName("message")
    @Expose
    private String massage;
    @SerializedName("data")
    @Expose
    private List<String> data;

    public StatusResponse(int statusCode, String massage, List<String> data) {
        this.statusCode = statusCode;
        this.massage = massage;
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
