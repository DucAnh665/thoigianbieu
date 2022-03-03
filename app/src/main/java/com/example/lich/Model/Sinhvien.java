package com.example.lich.Model;

import java.io.Serializable;

public class Sinhvien implements Serializable {
    int id;
    String Tensv,Masv,Ngaysinh,Taikhoan,Matkhau,khoa;

    public Sinhvien(int id, String tensv, String masv, String ngaysinh, String taikhoan, String matkhau, String khoa) {
        this.id = id;
        Tensv = tensv;
        Masv = masv;
        Ngaysinh = ngaysinh;
        Taikhoan = taikhoan;
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

    public String getNgaysinh() {
        return Ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        Ngaysinh = ngaysinh;
    }

    public String getTaikhoan() {
        return Taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        Taikhoan = taikhoan;
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
