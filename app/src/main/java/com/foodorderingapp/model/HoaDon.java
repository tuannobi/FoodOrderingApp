package com.foodorderingapp.model;

import java.util.Date;
import java.util.List;

public class HoaDon {
    private String hoaDonId;
    private List<ChiTietHoaDon> chitieHoaDon;
    private Date ngayTao;
    private Date ngayGiao;
    private Ship ship;
    private float tongTienThanhToan;
    private String tinNhan;
    private String trangThai;
    private String taiKhoanId;
    private Date thoiGianDatHang;
    private Date thoiGianChoGiaoHang;
    private Date thoiGianDangGiaoHang;
    private Date thoiGianGiaoHangThanhCong;
    private Date thoiGianHuy;
    private int danhGia;

    public HoaDon() {
    }

    public HoaDon(String hoaDonId,List<ChiTietHoaDon> chitieHoaDon, Date ngayTao, Date ngayGiao, Ship ship, float tongTienThanhToan, String tinNhan, String trangThai, String taiKhoanId, Date thoiGianDatHang, Date thoiGianChoGiaoHang, Date thoiGianDangGiaoHang, Date thoiGianGiaoHangThanhCong, Date thoiGianHuy, int danhGia) {
        this.hoaDonId = hoaDonId;
        this.chitieHoaDon = chitieHoaDon;
        this.ngayTao = ngayTao;
        this.ngayGiao = ngayGiao;
        this.ship = ship;
        this.tongTienThanhToan = tongTienThanhToan;
        this.tinNhan = tinNhan;
        this.trangThai = trangThai;
        this.taiKhoanId = taiKhoanId;
        this.thoiGianDatHang = thoiGianDatHang;
        this.thoiGianChoGiaoHang = thoiGianChoGiaoHang;
        this.thoiGianDangGiaoHang = thoiGianDangGiaoHang;
        this.thoiGianGiaoHangThanhCong = thoiGianGiaoHangThanhCong;
        this.thoiGianHuy = thoiGianHuy;
        this.danhGia = danhGia;
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

    public Date getNgayGiao() {
        return ngayGiao;
    }

    public void setNgayGiao(Date ngayGiao) {
        this.ngayGiao = ngayGiao;
    }



    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
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

    public List<ChiTietHoaDon> getChitieHoaDon() {
        return chitieHoaDon;
    }

    public void setChitieHoaDon(List<ChiTietHoaDon> chitieHoaDon) {
        this.chitieHoaDon = chitieHoaDon;
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

    public int getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(int danhGia) {
        this.danhGia = danhGia;
    }

    @Override
    public String toString() {
        return "HoaDon{" +
                "hoaDonId='" + hoaDonId + '\'' +
                ", ngayTao=" + ngayTao +
                ", ngayGiao=" + ngayGiao +
                ", tongTienThanhToan=" + tongTienThanhToan +
                ", tinNhan='" + tinNhan + '\'' +
                ", trangThai='" + trangThai + '\'' +
                ", khachHangId='" + taiKhoanId + '\'' +
                ", thoiGianDatHang=" + thoiGianDatHang +
                ", thoiGianChoGiaoHang=" + thoiGianChoGiaoHang +
                ", thoiGianDangGiaoHang=" + thoiGianDangGiaoHang +
                ", thoiGianGiaoHangThanhCong=" + thoiGianGiaoHangThanhCong +
                ", thoiGianHuy=" + thoiGianHuy +
                '}';
    }


}
