package com.foodorderingapp.model;

import java.util.Date;

public class KhuyenMai {
    private String khuyenMaiId;
    private Date ngayTao;
    private Date ngayHetHan;
    private float phanTramGiam;


    public KhuyenMai() {
    }

    public KhuyenMai(String khuyenMaiId, Date ngayTao, Date ngayHetHan, float phanTramGiam) {
        this.khuyenMaiId = khuyenMaiId;
        this.ngayTao = ngayTao;
        this.ngayHetHan = ngayHetHan;
        this.phanTramGiam = phanTramGiam;
    }

    public String getKhuyenMaiId() {
        return khuyenMaiId;
    }

    public void setKhuyenMaiId(String khuyenMaiId) {
        this.khuyenMaiId = khuyenMaiId;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(Date ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public float getPhantramgiam() {
        return phanTramGiam;
    }

    public void setPhantramgiam(float phanTramGiam) {
        this.phanTramGiam = phanTramGiam;
    }
}
