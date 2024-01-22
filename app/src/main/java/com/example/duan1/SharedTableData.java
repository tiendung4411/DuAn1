package com.example.duan1;

public class SharedTableData {
    private static SharedTableData instance;
    private int idBan;
    private int viTri;

    // Private constructor to prevent instantiation
    private SharedTableData() {
    }

    // Method to get the singleton instance
    public static SharedTableData getInstance() {
        if (instance == null) {
            instance = new SharedTableData();
        }
        return instance;
    }

    // Method to set the idBan
    public void setIdBan(int idBan) {
        this.idBan = idBan;
    }

    // Method to get the idBan
    public int getIdBan() {
        return idBan;
    }

    // Method to set the viTri
    public void setViTri(int viTri) {
        this.viTri = viTri;
    }

    // Method to get the viTri
    public int getViTri() {
        return viTri;
    }
}
