package com.poly.shopapp.model;

public class GioHang {
    private String tenSanpham;
    private int giaSanpham;
    private String soLuong;
    private int tongTien;
    private String ngay;

    public GioHang() {
    }

    public GioHang(String tenSanpham, int giaSanpham, String soLuong, int tongTien, String ngay) {
        this.tenSanpham = tenSanpham;
        this.giaSanpham = giaSanpham;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
        this.ngay = ngay;
    }

    public String getTenSanpham() {
        return tenSanpham;
    }

    public void setTenSanpham(String tenSanpham) {
        this.tenSanpham = tenSanpham;
    }

    public int getGiaSanpham() {
        return giaSanpham;
    }

    public void setGiaSanpham(int giaSanpham) {
        this.giaSanpham = giaSanpham;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }
}
