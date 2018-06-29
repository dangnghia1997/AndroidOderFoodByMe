package com.example.dangn.oderfoodbyme;

/**
 * Created by dangn on 6/28/2018.
 */

public class Ban {
    int idBan;
    String tenBan;
    int Hinh;

    public Ban(int idBan, String tenBan, int hinh) {
        this.idBan = idBan;
        this.tenBan = tenBan;
        Hinh = hinh;
    }

    public int getIdBan() {
        return idBan;
    }

    public void setIdBan(int idBan) {
        this.idBan = idBan;
    }

    public String getTenBan() {
        return tenBan;
    }

    public void setTenBan(String tenBan) {
        this.tenBan = tenBan;
    }

    public int getHinh() {
        return Hinh;
    }

    public void setHinh(int hinh) {
        Hinh = hinh;
    }

}
