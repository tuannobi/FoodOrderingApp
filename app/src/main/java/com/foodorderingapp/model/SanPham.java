package com.foodorderingapp.model;

public class SanPham {
    private String sanPhamId;
    private String tenSanPham;
    private float giaGoc;
    private float giaBanLe;
    private float kho;
    private String trangThai;
    private String phanLoaiId;

    public SanPham() {
    }

    public SanPham(String sanPhamId, String tenSanPham, float giaGoc, float giaBanLe, float kho, String trangThai, String phanLoaiId) {
        this.sanPhamId = sanPhamId;
        this.tenSanPham = tenSanPham;
        this.giaGoc = giaGoc;
        this.giaBanLe = giaBanLe;
        this.kho = kho;
        this.trangThai = trangThai;
        this.phanLoaiId = phanLoaiId;
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

    public float getGiaGoc() {
        return giaGoc;
    }

    public void setGiaGoc(float giaGoc) {
        this.giaGoc = giaGoc;
    }

    public float getGiaBanLe() {
        return giaBanLe;
    }

    public void setGiaBanLe(float giaBanLe) {
        this.giaBanLe = giaBanLe;
    }

    public float getKho() {
        return kho;
    }

    public void setKho(float kho) {
        this.kho = kho;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getPhanLoaiId() {
        return phanLoaiId;
    }

    public void setPhanLoaiId(String phanLoaiId) {
        this.phanLoaiId = phanLoaiId;
    }
}
