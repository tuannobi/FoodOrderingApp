package com.foodorderingapp.model;
import java.util.Date;
public class KhachHang {
    private String ho;
    private String ten;
    private Date ngaySinh;
    private String email;
    private String sdt;
    private String diaChi;
    private String taiKhoanId;
    private float doanhThu;
    public KhachHang() {
    }
    public KhachHang( String ho, String ten, Date ngaySinh, String email, String SDT, String diaChi, String taiKhoanId,float doanhThu) {
        this.ho = ho;
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.email = email;
        this.sdt = SDT;
        this.diaChi = diaChi;
        this.taiKhoanId = taiKhoanId;
        this.doanhThu = doanhThu;
    }
    public float getDoanhThu() {
        return doanhThu;
    }
    public void setDoanhThu(float doanhThu) {
        this.doanhThu = doanhThu;
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
        return sdt;
    }
    public void setSDT(String SDT) {
        this.sdt = SDT;
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
                ", ho='" + ho + '\'' +
                ", ten='" + ten + '\'' +
                ", ngaySinh=" + ngaySinh +
                ", email='" + email + '\'' +
                ", SDT='" + sdt + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", taiKhoanId='" + taiKhoanId + '\'' +
                '}';
    }
}