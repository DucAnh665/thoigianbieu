package com.example.lich.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRequest {

    @SerializedName("codeStudent")
    @Expose
    private String studentCode;
    @SerializedName("passWord")
    @Expose
    private  String passWord;

    public UserRequest(String studentCode, String passWord) {
        this.studentCode = studentCode;
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "studentCode='" + studentCode + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
