package com.poly.shopapp.model;

public class User {
    public String email;
    public String pass;
    public String sdt;
    public String diachi;

    public User(String email, String pass, String sdt, String diachi) {
        this.email = email;
        this.pass = pass;
        this.sdt = sdt;
        this.diachi = diachi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
}
