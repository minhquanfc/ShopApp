package com.poly.shopapp.model;

public class Danhmuc {
    public String anh;
    public String ten;
    public String loai;

    public Danhmuc() {
    }


    public Danhmuc(String anh, String ten, String loai) {
        this.anh = anh;
        this.ten = ten;
        this.loai = loai;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
