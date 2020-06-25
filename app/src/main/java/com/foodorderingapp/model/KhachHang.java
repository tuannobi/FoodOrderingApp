package com.foodorderingapp.model;

import java.util.Date;

public class KhachHang {
    private String khachHangId;
    private String ho;
    private String ten;
    private Date ngaySinh;
    private String email;
    private String SDT;
    private String diaChi;
    private String taiKhoanId;

    public KhachHang() {
    }

    public KhachHang(String khachHangId, String ho, String ten, Date ngaySinh, String email, String SDT, String diaChi, String taiKhoanId) {
        this.khachHangId = khachHangId;
        this.ho = ho;
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.email = email;
        this.SDT = SDT;
        this.diaChi = diaChi;
        this.taiKhoanId = taiKhoanId;
    }

    public String getKhachHangId() {
        return khachHangId;
    }

    public void setKhachHangId(String khachHangId) {
        this.khachHangId = khachHangId;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getTaiKhoanId() {
        return taiKhoanId;
    }

    public void setTaiKhoanId(String taiKhoanId) {
        this.taiKhoanId = taiKhoanId;
    }

    @Override
    public String toString() {
        return "KhachHang{" +
                "khachHangId='" + khachHangId + '\'' +
                ", ho='" + ho + '\'' +
                ", ten='" + ten + '\'' +
                ", ngaySinh=" + ngaySinh +
                ", email='" + email + '\'' +
                ", SDT='" + SDT + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", taiKhoanId='" + taiKhoanId + '\'' +
                '}';
    }
}
