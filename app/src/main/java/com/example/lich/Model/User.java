package com.example.lich.Model;


import java.util.List;

public class User {
    private int statusCode;
    private String message;
    private List<Data> data;

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

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data {
        private String userName;
        private String codeStudent;
        private String nameFaculty;
        private String nameClass;
        private String image;
        private String passWord;
        private String address;
        private String birthday;

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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }
    }
}



