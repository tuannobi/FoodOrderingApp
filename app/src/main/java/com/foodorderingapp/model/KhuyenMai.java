package com.foodorderingapp.model;

import com.google.firebase.Timestamp;

import java.util.Date;

public class KhuyenMai {
    private String khuyenMaiId;
    private Timestamp ngayTao;
    private Timestamp ngayHetHan;
    private float phanTramGiam;


    public KhuyenMai() {
    }

    public KhuyenMai(String khuyenMaiId, Timestamp ngayTao, Timestamp ngayHetHan, float phanTramGiam) {
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

    public Timestamp getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Timestamp ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Timestamp getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(Timestamp ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public float getPhantramgiam() {
        return phanTramGiam;
    }

    public void setPhantramgiam(float phanTramGiam) {
        this.phanTramGiam = phanTramGiam;
    }
}
