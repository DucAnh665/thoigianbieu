package com.example.lich.Model;

import java.io.Serializable;

public class Sinhvien implements Serializable {
    int id;
    String Tensv,Masv,Matkhau,khoa;

    public Sinhvien(int id, String tensv, String masv, String matkhau, String khoa) {
        this.id = id;
        Tensv = tensv;
        Masv = masv;
        Matkhau = matkhau;
        this.khoa = khoa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTensv() {
        return Tensv;
    }

    public void setTensv(String tensv) {
        Tensv = tensv;
    }

    public String getMasv() {
        return Masv;
    }

    public void setMasv(String masv) {
        Masv = masv;
    }

    public String getMatkhau() {
        return Matkhau;
    }

    public void setMatkhau(String matkhau) {
        Matkhau = matkhau;
    }

    public String getKhoa() {
        return khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }
}
