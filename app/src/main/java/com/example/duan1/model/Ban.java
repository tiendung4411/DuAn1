package com.example.duan1.model;

public class Ban {
    int idBan;
    int viTri;

    public Ban(int idBan, int viTri) {
        this.idBan = idBan;
        this.viTri = viTri;
    }

    public Ban(int viTri) {
        this.viTri = viTri;
    }

    public int getIdBan() {
        return idBan;
    }

    public void setIdBan(int idBan) {
        this.idBan = idBan;
    }

    public int getViTri() {
        return viTri;
    }

    public void setViTri(int viTri) {
        this.viTri = viTri;
    }
}
