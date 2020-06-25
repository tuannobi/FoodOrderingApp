package com.foodorderingapp.model;

public class ChiTietHoaDon {
    private String hoaDonId;
    private String sanPhamId;
    private int soLuong;
    private float tienKhuyenMai;
    private float tongTien;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(String hoaDonId, String sanPhamId, int soLuong, float tienKhuyenMai, float tongTien) {
        this.hoaDonId = hoaDonId;
        this.sanPhamId = sanPhamId;
        this.soLuong = soLuong;
        this.tienKhuyenMai = tienKhuyenMai;
        this.tongTien = tongTien;
    }

    public String getHoaDonId() {
        return hoaDonId;
    }

    public void setHoaDonId(String hoaDonId) {
        this.hoaDonId = hoaDonId;
    }

    public String getSanPhamId() {
        return sanPhamId;
    }

    public void setSanPhamId(String sanPhamId) {
        this.sanPhamId = sanPhamId;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getTienKhuyenMai() {
        return tienKhuyenMai;
    }

    public void setTienKhuyenMai(float tienKhuyenMai) {
        this.tienKhuyenMai = tienKhuyenMai;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }
}
