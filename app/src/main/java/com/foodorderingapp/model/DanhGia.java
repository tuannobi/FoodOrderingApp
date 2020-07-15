package com.foodorderingapp.model;

import java.io.Serializable;

public class DanhGia implements Serializable {
    private String danhGiaId;
    private int hangMuc;
    private String nhanXet;
    private String sanPhamId;
    private String taiKhoanId;

    public DanhGia() {
    }

    public DanhGia(String danhGiaId, int hangMuc, String nhanXet, String sanPhamId, String taiKhoanId) {
        this.danhGiaId = danhGiaId;
        this.hangMuc = hangMuc;
        this.nhanXet = nhanXet;
        this.sanPhamId = sanPhamId;
        this.taiKhoanId = taiKhoanId;
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

    public String getTaiKhoanId() {
        return taiKhoanId;
    }

    public void setTaiKhoanId(String taiKhoanId) {
        this.taiKhoanId =taiKhoanId;
    }
}
