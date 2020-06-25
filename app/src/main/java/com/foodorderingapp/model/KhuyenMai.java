package com.foodorderingapp.model;

public class KhuyenMai {
    private String khuyenMaiId;
    private String tenKhuyenMai;
    private String hinhAnh;
    private String moTa;
    private String trangThai;

    public KhuyenMai() {
    }

    public KhuyenMai(String khuyenMaiId, String tenKhuyenMai, String hinhAnh, String moTa, String trangThai) {
        this.khuyenMaiId = khuyenMaiId;
        this.tenKhuyenMai = tenKhuyenMai;
        this.hinhAnh = hinhAnh;
        this.moTa = moTa;
        this.trangThai = trangThai;
    }

    public String getKhuyenMaiId() {
        return khuyenMaiId;
    }

    public void setKhuyenMaiId(String khuyenMaiId) {
        this.khuyenMaiId = khuyenMaiId;
    }

    public String getTenKhuyenMai() {
        return tenKhuyenMai;
    }

    public void setTenKhuyenMai(String tenKhuyenMai) {
        this.tenKhuyenMai = tenKhuyenMai;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
