package com.example.lich.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {
    @SerializedName("statusCode")
    @Expose
    private int statusCode;
    @SerializedName("message")
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

    public static class User {
        @SerializedName("userName")
        @Expose
        public String userName;
        @SerializedName("codeStudent")
        @Expose
        public String codeStudent;
        @SerializedName("nameFaculty")
        @Expose
        public String nameFaculty;
        @SerializedName("nameClass")
        @Expose
        public String nameClass;

        @SerializedName("image")
        @Expose
        public String image;

        @SerializedName("passWord")
        @Expose
        public String passWord;

        public User(String userName, String codeStudent, String nameFaculty, String nameClass, String image, String passWord) {
            this.userName = userName;
            this.codeStudent = codeStudent;
            this.nameFaculty = nameFaculty;
            this.nameClass = nameClass;
            this.image = image;
            this.passWord = passWord;
        }

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

        public String getNameFaculty() {
            return nameFaculty;
        }

        public void setNameFaculty(String nameFaculty) {
            this.nameFaculty = nameFaculty;
        }

        public String getNameClass() {
            return nameClass;
        }

        public void setNameClass(String nameClass) {
            this.nameClass = nameClass;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getPassWord() {
            return passWord;
        }

        public void setPassWord(String passWord) {
            this.passWord = passWord;
        }
    }
}


