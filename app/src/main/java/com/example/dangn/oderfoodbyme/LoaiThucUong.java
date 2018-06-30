package com.example.dangn.oderfoodbyme;

/**
 * Created by dangn on 6/29/2018.
 */

public class LoaiThucUong {
    private  int ma_loai;
    private String tenLoai;
    private int imgHinh;

    public LoaiThucUong(int ma_loai, String tenLoai, int imgHinh) {
        this.ma_loai = ma_loai;
        this.tenLoai = tenLoai;
        this.imgHinh = imgHinh;
    }

    public int getMa_loai() {
        return ma_loai;
    }

    public void setMa_loai(int ma_loai) {
        this.ma_loai = ma_loai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public int getImgHinh() {
        return imgHinh;
    }

    public void setImgHinh(int imgHinh) {
        this.imgHinh = imgHinh;
    }
}
