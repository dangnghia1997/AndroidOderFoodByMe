package com.example.dangn.oderfoodbyme;

/**
 * Created by dangn on 6/29/2018.
 */

public class ThucUongTheoMaLoai {
    int ma_thuc_uong;
    String ten_thuc_uong;
    double gia;
    int soluong;
    int hinh;

    public ThucUongTheoMaLoai(int ma_thuc_uong, String ten_thuc_uong, double gia, int soluong, int hinh) {
        this.ma_thuc_uong = ma_thuc_uong;
        this.ten_thuc_uong = ten_thuc_uong;
        this.gia = gia;
        this.soluong = soluong;
        this.hinh = hinh;
    }

    public int getMa_thuc_uong() {
        return ma_thuc_uong;
    }

    public void setMa_thuc_uong(int ma_thuc_uong) {
        this.ma_thuc_uong = ma_thuc_uong;
    }

    public String getTen_thuc_uong() {
        return ten_thuc_uong;
    }

    public void setTen_thuc_uong(String ten_thuc_uong) {
        this.ten_thuc_uong = ten_thuc_uong;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }
}
