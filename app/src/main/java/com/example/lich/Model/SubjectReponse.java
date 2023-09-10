package com.example.lich.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubjectReponse {

    @SerializedName("statusCode")
    @Expose
    private int statusCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("codeStudent")
    @Expose
    private String codeStudent;
    @SerializedName("data")
    @Expose
    private List<Subject> data;


    public SubjectReponse(int statusCode, String message, String codeStudent, List<Subject> data) {
        this.statusCode = statusCode;
        this.message = message;
        this.codeStudent = codeStudent;
        this.data = data;
    }


    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCodeStudent() {
        return codeStudent;
    }

    public void setCodeStudent(String codeStudent) {
        this.codeStudent = codeStudent;
    }

    public List<Subject> getData() {
        return data;
    }

    public void setData(List<Subject> data) {
        this.data = data;
    }

    public class Subject {

        @SerializedName("nameSubject")
        @Expose
        private String nameSubject;
        @SerializedName("dateTime")
        @Expose
        private String dateTime;
        @SerializedName("nameTeacher")
        @Expose
        private String nameTeacher;

        @SerializedName("number")
        @Expose
        private int number;

        public Subject(String nameSubject, String dateTime, String nameTeacher, int number) {
            this.nameSubject = nameSubject;
            this.dateTime = dateTime;
            this.nameTeacher = nameTeacher;
            this.number = number;
        }

        public String getNameSubject() {
            return nameSubject;
        }

        public void setNameSubject(String nameSubject) {
            this.nameSubject = nameSubject;
        }

        public String getDateTime() {
            return dateTime;
        }

        public void setDateTime(String dateTime) {
            this.dateTime = dateTime;
        }

        public String getNameTeacher() {
            return nameTeacher;
        }

        public void setNameTeacher(String nameTeacher) {
            this.nameTeacher = nameTeacher;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }
}
