package com.foodorderingapp.model;

import java.util.Date;
import java.util.List;

public class HoaDon {
    private String hoaDonId;
    public List<ChiTietHoaDon> chiTietHoaDon;
    private Date ngayTao;
    private Date ngayGiao;
    private float phiShip;
    private String quan;
    private String soNha;
    private float tongTienThanhToan;
    private float tienKhuyenMai;
    private String maKhuyenMai;
    private String trangThai;
    private String taiKhoanId;
    private Date thoiGianDatHang;
    private Date thoiGianChoGiaoHang;
    private Date thoiGianDangGiaoHang;
    private Date thoiGianGiaoHangThanhCong;
    private Date thoiGianHuy;

    public HoaDon() {
    }

    public HoaDon(String hoaDonId, List<ChiTietHoaDon> chiTietHoaDon, Date ngayTao, Date ngayGiao, float phiShip, String quan, String soNha, float tongTienThanhToan, float tienKhuyenMai, String maKhuyenMai, String trangThai, String taiKhoanId, Date thoiGianDatHang, Date thoiGianChoGiaoHang, Date thoiGianDangGiaoHang, Date thoiGianGiaoHangThanhCong, Date thoiGianHuy) {
        this.hoaDonId = hoaDonId;
        this.chiTietHoaDon = chiTietHoaDon;
        this.ngayTao = ngayTao;
        this.ngayGiao = ngayGiao;
        this.phiShip = phiShip;
        this.quan = quan;
        this.soNha = soNha;
        this.tongTienThanhToan = tongTienThanhToan;
        this.tienKhuyenMai = tienKhuyenMai;
        this.maKhuyenMai = maKhuyenMai;
        this.trangThai = trangThai;
        this.taiKhoanId = taiKhoanId;
        this.thoiGianDatHang = thoiGianDatHang;
        this.thoiGianChoGiaoHang = thoiGianChoGiaoHang;
        this.thoiGianDangGiaoHang = thoiGianDangGiaoHang;
        this.thoiGianGiaoHangThanhCong = thoiGianGiaoHangThanhCong;
        this.thoiGianHuy = thoiGianHuy;
    }

    public String getHoaDonId() {
        return hoaDonId;
    }

    public void setHoaDonId(String hoaDonId) {
        this.hoaDonId = hoaDonId;
    }

    public List<ChiTietHoaDon> getChiTietHoaDon() {
        return chiTietHoaDon;
    }

    public void setChiTietHoaDon(List<ChiTietHoaDon> chiTietHoaDon) {
        this.chiTietHoaDon = chiTietHoaDon;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgayGiao() {
        return ngayGiao;
    }

    public void setNgayGiao(Date ngayGiao) {
        this.ngayGiao = ngayGiao;
    }

    public float getPhiShip() {
        return phiShip;
    }

    public void setPhiShip(float phiShip) {
        this.phiShip = phiShip;
    }

    public String getQuan() {
        return quan;
    }

    public void setQuan(String quan) {
        this.quan = quan;
    }

    public String getSoNha() {
        return soNha;
    }

    public void setSoNha(String soNha) {
        this.soNha = soNha;
    }

    public float getTongTienThanhToan() {
        return tongTienThanhToan;
    }

    public void setTongTienThanhToan(float tongTienThanhToan) {
        this.tongTienThanhToan = tongTienThanhToan;
    }

    public float getTienKhuyenMai() {
        return tienKhuyenMai;
    }

    public void setTienKhuyenMai(float tienKhuyenMai) {
        this.tienKhuyenMai = tienKhuyenMai;
    }

    public String getMaKhuyenMai() {
        return maKhuyenMai;
    }

    public void setMaKhuyenMai(String maKhuyenMai) {
        this.maKhuyenMai = maKhuyenMai;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getTaiKhoanId() {
        return taiKhoanId;
    }

    public void setTaiKhoanId(String taiKhoanId) {
        this.taiKhoanId = taiKhoanId;
    }

    public Date getThoiGianDatHang() {
        return thoiGianDatHang;
    }

    public void setThoiGianDatHang(Date thoiGianDatHang) {
        this.thoiGianDatHang = thoiGianDatHang;
    }

    public Date getThoiGianChoGiaoHang() {
        return thoiGianChoGiaoHang;
    }

    public void setThoiGianChoGiaoHang(Date thoiGianChoGiaoHang) {
        this.thoiGianChoGiaoHang = thoiGianChoGiaoHang;
    }

    public Date getThoiGianDangGiaoHang() {
        return thoiGianDangGiaoHang;
    }

    public void setThoiGianDangGiaoHang(Date thoiGianDangGiaoHang) {
        this.thoiGianDangGiaoHang = thoiGianDangGiaoHang;
    }

    public Date getThoiGianGiaoHangThanhCong() {
        return thoiGianGiaoHangThanhCong;
    }

    public void setThoiGianGiaoHangThanhCong(Date thoiGianGiaoHangThanhCong) {
        this.thoiGianGiaoHangThanhCong = thoiGianGiaoHangThanhCong;
    }

    public Date getThoiGianHuy() {
        return thoiGianHuy;
    }

    public void setThoiGianHuy(Date thoiGianHuy) {
        this.thoiGianHuy = thoiGianHuy;
    }
}
