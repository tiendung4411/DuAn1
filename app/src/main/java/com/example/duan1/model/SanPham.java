package com.example.duan1.model;

public class SanPham {
    String name;
    String tax;
    int pic;

    public SanPham(String name, String tax, int pic) {
        this.name = name;
        this.tax = tax;
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }
}
