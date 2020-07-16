package com.foodorderingapp.model;

import java.io.Serializable;

public class SanPham implements Serializable {
    private String hinhAnhId;
    private String sanPhamId;
    private String tenSanPham;
    private float giaGoc;
    private float giaBanLe;
    private String trangThai;
    private String phanLoaiId;
    private String moTa;
    private float doanhThu;
    private int soLuongNguoiMua=0;
    public SanPham() {
    }

    public int getSoLuongNguoiMua() {
        return soLuongNguoiMua;
    }

    public void setSoLuongNguoiMua(int soLuongNguoiMua) {
        this.soLuongNguoiMua = soLuongNguoiMua;
    }

    public SanPham(String sanPhamId, String tenSanPham, float giaGoc, float giaBanLe, String trangThai, String phanLoaiId, String hinhAnhId, String moTa, float doanhThu) {
        this.sanPhamId = sanPhamId;
        this.tenSanPham = tenSanPham;
        this.giaGoc = giaGoc;
        this.giaBanLe = giaBanLe;
        this.trangThai = trangThai;
        this.phanLoaiId = phanLoaiId;
        this.hinhAnhId = hinhAnhId;
        this.moTa = moTa;
        this.doanhThu = doanhThu;
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

    public String getHinhAnhId() {
        return hinhAnhId;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    public String getMoTa() {
        return moTa;
    }

    public void setHinhAnhId(String hinhAnhId) {
        this.hinhAnhId = hinhAnhId;
    }

    public float getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(float doanhThu) {
        this.doanhThu = doanhThu;
    }
}
