package com.foodorderingapp.model;

public class PhanLoai {
    private String phanLoaiId;
    private String loai;
    private String moTa;

    public PhanLoai() {
    }

    public PhanLoai(String phanLoaiId, String loai, String moTa) {
        this.phanLoaiId = phanLoaiId;
        this.loai = loai;
        this.moTa = moTa;
    }

    public String getPhanLoaiId() {
        return phanLoaiId;
    }

    public void setPhanLoaiId(String phanLoaiId) {
        this.phanLoaiId = phanLoaiId;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
