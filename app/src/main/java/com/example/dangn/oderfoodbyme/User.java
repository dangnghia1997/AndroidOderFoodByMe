package com.example.dangn.oderfoodbyme;

/**
 * Created by dangn on 6/27/2018.
 */

public class User {
    int id;
    String ten_dang_nhap;
    String password;
    String ho_ten;

    public User(int id, String ten_dang_nhap, String password, String ho_ten) {
        this.id = id;
        this.ten_dang_nhap = ten_dang_nhap;
        this.password = password;
        this.ho_ten = ho_ten;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen_dang_nhap() {
        return ten_dang_nhap;
    }

    public void setTen_dang_nhap(String ten_dang_nhap) {
        this.ten_dang_nhap = ten_dang_nhap;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHo_ten() {
        return ho_ten;
    }

    public void setHo_ten(String ho_ten) {
        this.ho_ten = ho_ten;
    }
};
