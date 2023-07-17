package com.example.duan1.Model;

public class SanPham {
    int ImvPic;
    String TvName;
    String TvGia;

    public SanPham(int imvPic, String tvName, String tvGia) {
        this.ImvPic = imvPic;
        this.TvName = tvName;
        this.TvGia = tvGia;
    }

    public int getImvPic() {
        return ImvPic;
    }

    public void setImvPic(int imvPic) {
        this.ImvPic = imvPic;
    }

    public String getTvName() {
        return TvName;
    }

    public void setTvName(String tvName) {
        this.TvName = tvName;
    }

    public String getTvGia() {
        return TvGia;
    }

    public void setTvGia(String tvGia) {
        this.TvGia = tvGia;
    }
}
