package com.foodorderingapp.model;

public class DanhGia {
    private String danhGiaId;
    private int hangMuc;
    private String nhanXet;
    private String sanPhamId;
    private String khachHangId;

    public DanhGia() {
    }

    public DanhGia(String danhGiaId, int hangMuc, String nhanXet, String sanPhamId, String khachHangId) {
        this.danhGiaId = danhGiaId;
        this.hangMuc = hangMuc;
        this.nhanXet = nhanXet;
        this.sanPhamId = sanPhamId;
        this.khachHangId = khachHangId;
    }

    public String getDanhGiaId() {
        return danhGiaId;
    }

    public void setDanhGiaId(String danhGiaId) {
        this.danhGiaId = danhGiaId;
    }

    public int getHangMuc() {
        return hangMuc;
    }

    public void setHangMuc(int hangMuc) {
        this.hangMuc = hangMuc;
    }

    public String getNhanXet() {
        return nhanXet;
    }

    public void setNhanXet(String nhanXet) {
        this.nhanXet = nhanXet;
    }

    public String getSanPhamId() {
        return sanPhamId;
    }

    public void setSanPhamId(String sanPhamId) {
        this.sanPhamId = sanPhamId;
    }

    public String getKhachHangId() {
        return khachHangId;
    }

    public void setKhachHangId(String khachHangId) {
        this.khachHangId = khachHangId;
    }
}
