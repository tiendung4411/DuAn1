package com.example.duan1.model;

public class Cart {
    String name;
    String sl;
    String tien;
    String tongtien;
    int img;

    public Cart(String name, String sl, String tien, String tongtien, int img) {
        this.name = name;
        this.sl = sl;
        this.tien = tien;
        this.tongtien = tongtien;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public String getTien() {
        return tien;
    }

    public void setTien(String tien) {
        this.tien = tien;
    }

    public String getTongtien() {
        return tongtien;
    }

    public void setTongtien(String tongtien) {
        this.tongtien = tongtien;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
