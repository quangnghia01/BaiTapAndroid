package com.example.model;

import java.io.Serializable;

public class BaiHat implements Serializable {
    String tenBH;
    String ngayRaDia;
    String maAlbum;

    public BaiHat() {
    }

    @Override
    public String toString() {
        return maAlbum + " -- " + tenBH + " - "+ ngayRaDia;
    }

    public String getNgayRaDia() {
        return ngayRaDia;
    }

    public void setNgayRaDia(String ngayRaDia) {
        this.ngayRaDia = ngayRaDia;
    }

    public String getMaAlbum() {
        return maAlbum;
    }

    public void setMaAlbum(String maAlbum) {
        this.maAlbum = maAlbum;
    }

    public String getTenBH() {
        return tenBH;
    }

    public void setTenBH(String tenBH) {
        this.tenBH = tenBH;
    }

    public BaiHat(String tenBH, String ngayRaDia, String maAlbum) {
        this.tenBH = tenBH;
        this.ngayRaDia = ngayRaDia;
        this.maAlbum = maAlbum;
    }
}
