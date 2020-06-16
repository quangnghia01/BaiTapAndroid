package com.example.model;

import java.io.Serializable;

public class Album implements Serializable {
    String ma;
    String ten;

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public Album(String ma, String ten) {
        this.ma = ma;
        this.ten = ten;
    }

    public Album() {
    }

    @Override
    public String toString() {
        return ma +" - "+ ten;
    }
}
