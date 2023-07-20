package com.example.lich.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {
    @SerializedName("statusCode")
    @Expose
    private int statusCode;
    @SerializedName("massage")
    @Expose
    private String massage;
    @SerializedName("data")
    @Expose
    private List<User> data;

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

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "statusCode=" + statusCode +
                ", massage='" + massage + '\'' +
                ", data=" + data +
                '}';
    }

    class User {
        @SerializedName("userName")
        @Expose
        public String userName;
        @SerializedName("codeStudent")
        @Expose
        public String codeStudent;
        @SerializedName("idFaculty")
        @Expose
        public String idFaculty;
        @SerializedName("idClass")
        @Expose
        public String idClass;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getCodeStudent() {
            return codeStudent;
        }

        public void setCodeStudent(String codeStudent) {
            this.codeStudent = codeStudent;
        }

        public String getIdFaculty() {
            return idFaculty;
        }

        public void setIdFaculty(String idFaculty) {
            this.idFaculty = idFaculty;
        }

        public String getIdClass() {
            return idClass;
        }

        public void setIdClass(String idClass) {
            this.idClass = idClass;
        }
    }
}


