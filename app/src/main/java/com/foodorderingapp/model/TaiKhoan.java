package com.foodorderingapp.model;

import java.util.Date;

public class TaiKhoan {
    private String taiKhoanId;
    private String userName;
    private String password;
    private Date ngayTao;
    private int kichHoat;
    private String vaiTroId;

    public TaiKhoan() {
    }

    public TaiKhoan(String taiKhoanId, String userName, String password, Date ngayTao, int kichHoat, String vaiTroId) {
        this.taiKhoanId = taiKhoanId;
        this.userName = userName;
        this.password = password;
        this.ngayTao = ngayTao;
        this.kichHoat = kichHoat;
        this.vaiTroId = vaiTroId;
    }

    public String getTaiKhoanId() {
        return taiKhoanId;
    }

    public void setTaiKhoanId(String taiKhoanId) {
        this.taiKhoanId = taiKhoanId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public int getKichHoat() {
        return kichHoat;
    }

    public void setKichHoat(int kichHoat) {
        this.kichHoat = kichHoat;
    }

    public String getVaiTroId() {
        return vaiTroId;
    }

    public void setVaiTroId(String vaiTroId) {
        this.vaiTroId = vaiTroId;
    }

    @Override
    public String toString() {
        return "TaiKhoan{" +
                "taiKhoanId='" + taiKhoanId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", ngayTao=" + ngayTao +
                ", kichHoat=" + kichHoat +
                ", vaiTroId='" + vaiTroId + '\'' +
                '}';
    }
}
