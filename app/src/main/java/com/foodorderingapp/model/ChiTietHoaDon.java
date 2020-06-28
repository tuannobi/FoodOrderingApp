package com.foodorderingapp.model;

public class ChiTietHoaDon {
    private String sanPhamId;
    private String tenSanPham;
    private float gia;
    private int soLuong;
    private float tongTien;
    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(String sanPhamId, String tenSanPham, float gia, int soLuong, float tongTien) {
        this.sanPhamId = sanPhamId;
        this.tenSanPham = tenSanPham;
        this.gia = gia;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
    }

    public String getSanPhamId() {
        return sanPhamId;
    }

    public void setSanPhamId(String sanPhamId) {
        this.sanPhamId = sanPhamId;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }
}
