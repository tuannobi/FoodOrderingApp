package com.foodorderingapp.model;

import java.util.Date;

public class HoaDon {
    private String hoaDonId;
    private Date ngayTao;
    private String phuongThucThanhToan;
    private Date ngayGiao;
    private String soNha;
    private String quan;
    private String phiShipId;
    private float tongTienThanhToan;
    private String tinNhan;
    private String trangThai;
    private String khachHangId;
    private String nhanVienBanHangId;
    private String nhanVienGiaoHangId;
    private Date thoiGianDatHang;
    private Date thoiGianChoGiaoHang;
    private Date thoiGianDangGiaoHang;
    private Date thoiGianGiaoHangThanhCong;
    private Date thoiGianHuy;
    private int danhGia;

    public HoaDon() {
    }

    public HoaDon(String hoaDonId, Date ngayTao, String phuongThucThanhToan, Date ngayGiao, String soNha, String quan, String phiShipId, float tongTienThanhToan, String tinNhan, String trangThai, String khachHangId, String nhanVienBanHangId, String nhanVienGiaoHangId, Date thoiGianDatHang, Date thoiGianChoGiaoHang, Date thoiGianDangGiaoHang, Date thoiGianGiaoHangThanhCong, Date thoiGianHuy) {
        this.hoaDonId = hoaDonId;
        this.ngayTao = ngayTao;
        this.phuongThucThanhToan = phuongThucThanhToan;
        this.ngayGiao = ngayGiao;
        this.soNha = soNha;
        this.quan = quan;
        this.phiShipId = phiShipId;
        this.tongTienThanhToan = tongTienThanhToan;
        this.tinNhan = tinNhan;
        this.trangThai = trangThai;
        this.khachHangId = khachHangId;
        this.nhanVienBanHangId = nhanVienBanHangId;
        this.nhanVienGiaoHangId = nhanVienGiaoHangId;
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

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getPhuongThucThanhToan() {
        return phuongThucThanhToan;
    }

    public void setPhuongThucThanhToan(String phuongThucThanhToan) {
        this.phuongThucThanhToan = phuongThucThanhToan;
    }

    public Date getNgayGiao() {
        return ngayGiao;
    }

    public void setNgayGiao(Date ngayGiao) {
        this.ngayGiao = ngayGiao;
    }

    public String getSoNha() {
        return soNha;
    }

    public void setSoNha(String soNha) {
        this.soNha = soNha;
    }

    public String getQuan() {
        return quan;
    }

    public void setQuan(String quan) {
        this.quan = quan;
    }

    public String getPhiShipId() {
        return phiShipId;
    }

    public void setPhiShipId(String phiShipId) {
        this.phiShipId = phiShipId;
    }

    public float getTongTienThanhToan() {
        return tongTienThanhToan;
    }

    public void setTongTienThanhToan(float tongTienThanhToan) {
        this.tongTienThanhToan = tongTienThanhToan;
    }

    public String getTinNhan() {
        return tinNhan;
    }

    public void setTinNhan(String tinNhan) {
        this.tinNhan = tinNhan;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getKhachHangId() {
        return khachHangId;
    }

    public void setKhachHangId(String khachHangId) {
        this.khachHangId = khachHangId;
    }

    public String getNhanVienBanHangId() {
        return nhanVienBanHangId;
    }

    public void setNhanVienBanHangId(String nhanVienBanHangId) {
        this.nhanVienBanHangId = nhanVienBanHangId;
    }

    public String getNhanVienGiaoHangId() {
        return nhanVienGiaoHangId;
    }

    public void setNhanVienGiaoHangId(String nhanVienGiaoHangId) {
        this.nhanVienGiaoHangId = nhanVienGiaoHangId;
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

    @Override
    public String toString() {
        return "HoaDon{" +
                "hoaDonId='" + hoaDonId + '\'' +
                ", ngayTao=" + ngayTao +
                ", phuongThucThanhToan='" + phuongThucThanhToan + '\'' +
                ", ngayGiao=" + ngayGiao +
                ", soNha='" + soNha + '\'' +
                ", quan='" + quan + '\'' +
                ", phiShipId='" + phiShipId + '\'' +
                ", tongTienThanhToan=" + tongTienThanhToan +
                ", tinNhan='" + tinNhan + '\'' +
                ", trangThai='" + trangThai + '\'' +
                ", khachHangId='" + khachHangId + '\'' +
                ", nhanVienBanHangId='" + nhanVienBanHangId + '\'' +
                ", nhanVienGiaoHangId='" + nhanVienGiaoHangId + '\'' +
                ", thoiGianDatHang=" + thoiGianDatHang +
                ", thoiGianChoGiaoHang=" + thoiGianChoGiaoHang +
                ", thoiGianDangGiaoHang=" + thoiGianDangGiaoHang +
                ", thoiGianGiaoHangThanhCong=" + thoiGianGiaoHangThanhCong +
                ", thoiGianHuy=" + thoiGianHuy +
                '}';
    }

    public int getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(int danhGia) {
        this.danhGia = danhGia;
    }
}
