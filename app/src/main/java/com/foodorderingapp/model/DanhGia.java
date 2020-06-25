package com.foodorderingapp.model;

public class DanhGia {
    private String danhGiaId;
    private int hangMuc;
    private String nhanXet;
    private String hoaDonId;
    private String khachHangId;

    public DanhGia() {
    }

    public DanhGia(String danhGiaId, int hangMuc, String nhanXet, String hoaDonId, String khachHangId) {
        this.danhGiaId = danhGiaId;
        this.hangMuc = hangMuc;
        this.nhanXet = nhanXet;
        this.hoaDonId = hoaDonId;
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

    public String getHoaDonId() {
        return hoaDonId;
    }

    public void setHoaDonId(String hoaDonId) {
        this.hoaDonId = hoaDonId;
    }

    public String getKhachHangId() {
        return khachHangId;
    }

    public void setKhachHangId(String khachHangId) {
        this.khachHangId = khachHangId;
    }
}
