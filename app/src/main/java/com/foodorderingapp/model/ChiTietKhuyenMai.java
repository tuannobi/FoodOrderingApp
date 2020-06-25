package com.foodorderingapp.model;

import java.util.Date;

public class ChiTietKhuyenMai {
    private String sanPhamId;
    private String khuyenMaiId;
    private float phanTramGiam;
    private Date ngayBatDau;
    private Date ngayKetThuc;

    public ChiTietKhuyenMai() {
    }

    public ChiTietKhuyenMai(String sanPhamId, String khuyenMaiId, float phanTramGiam, Date ngayBatDau, Date ngayKetThuc) {
        this.sanPhamId = sanPhamId;
        this.khuyenMaiId = khuyenMaiId;
        this.phanTramGiam = phanTramGiam;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getSanPhamId() {
        return sanPhamId;
    }

    public void setSanPhamId(String sanPhamId) {
        this.sanPhamId = sanPhamId;
    }

    public String getKhuyenMaiId() {
        return khuyenMaiId;
    }

    public void setKhuyenMaiId(String khuyenMaiId) {
        this.khuyenMaiId = khuyenMaiId;
    }

    public float getPhanTramGiam() {
        return phanTramGiam;
    }

    public void setPhanTramGiam(float phanTramGiam) {
        this.phanTramGiam = phanTramGiam;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }
}
