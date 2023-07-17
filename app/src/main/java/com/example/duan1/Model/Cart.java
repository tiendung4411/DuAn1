package com.example.duan1.Model;

public class Cart {
    String TvName;
    String TvTien;
    String TvsoLuong;
    String TvtongTien;

    int caffe;

    public Cart(String tvName, String tvTien, String tvsoLuong, String tvtongTien, int caffe) {
        this.TvName = tvName;
        this.TvTien = tvTien;
        this.TvsoLuong = tvsoLuong;
        this.TvtongTien = tvtongTien;
        this.caffe = caffe;
    }

    public String getTvName() {
        return TvName;
    }

    public void setTvName(String tvName) {
        this.TvName = tvName;
    }

    public String getTvTien() {
        return TvTien;
    }

    public void setTvTien(String tvTien) {
        this.TvTien = tvTien;
    }

    public String getTvsoLuong() {
        return TvsoLuong;
    }

    public void setTvsoLuong(String tvsoLuong) {
        this.TvsoLuong = tvsoLuong;
    }

    public String getTvtongTien() {
        return TvtongTien;
    }

    public void setTvtongTien(String tvtongTien) {
        this.TvtongTien = tvtongTien;
    }

    public int getCaffe() {
        return caffe;
    }

    public void setCaffe(int caffe) {
        this.caffe = caffe;
    }
}
