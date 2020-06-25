package com.foodorderingapp.model;

import java.util.Date;

public class Admin {
    private String adminId;
    private String ho;
    private String ten;
    private Date ngaySinh;
    private String email;
    private String SDT;
    private String diaChi;
    private String taiKhoanId;

    public Admin() {
    }

    public Admin(String adminId, String ho, String ten, Date ngaySinh, String email, String SDT, String diaChi, String taiKhoanId) {
        this.adminId = adminId;
        this.ho = ho;
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.email = email;
        this.SDT = SDT;
        this.diaChi = diaChi;
        this.taiKhoanId = taiKhoanId;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
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
}
