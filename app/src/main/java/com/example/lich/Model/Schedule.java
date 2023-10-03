package com.example.lich.Model;

public class Schedule {
    int id;
    String tenmon,ngay,thang,nam,khoa,giangvien;


    public Schedule(int id, String tenmon, String ngay, String thang, String nam, String khoa, String giangvien) {
        this.id = id;
        this.tenmon = tenmon;
        this.ngay = ngay;
        this.thang = thang;
        this.nam = nam;
        this.khoa = khoa;
        this.giangvien = giangvien;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenmon() {
        return tenmon;
    }

    public void setTenmon(String tenmon) {
        this.tenmon = tenmon;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getThang() {
        return thang;
    }

    public void setThang(String thang) {
        this.thang = thang;
    }

    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    public String getKhoa() {
        return khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }

    public String getGiangvien() {
        return giangvien;
    }

    public void setGiangvien(String giangvien) {
        this.giangvien = giangvien;
    }
}
