package com.example.duan1.model;

public class Mon {
    private int idMon;
    private String tenMon;
    private int gia;
    private String moTa;
    private int soLuong;
    private int idLoaiMon;
    private String imgUrl;

    public int getIdMon() {
        return idMon;
    }

    public void setIdMon(int idMon) {
        this.idMon = idMon;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getIdLoaiMon() {
        return idLoaiMon;
    }

    public void setIdLoaiMon(int idLoaiMon) {
        this.idLoaiMon = idLoaiMon;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Mon(String tenMon, int gia, String moTa, int soLuong, int idLoaiMon, String imgUrl) {
        this.tenMon = tenMon;
        this.gia = gia;
        this.moTa = moTa;
        this.soLuong = soLuong;
        this.idLoaiMon = idLoaiMon;
        this.imgUrl = imgUrl;
    }

    public Mon(int idMon, String tenMon, int gia, String moTa, int soLuong, int idLoaiMon, String imgUrl) {
        this.idMon = idMon;
        this.tenMon = tenMon;
        this.gia = gia;
        this.moTa = moTa;
        this.soLuong = soLuong;
        this.idLoaiMon = idLoaiMon;
        this.imgUrl = imgUrl;
    }
}
