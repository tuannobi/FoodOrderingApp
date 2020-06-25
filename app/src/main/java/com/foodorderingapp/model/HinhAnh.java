package com.foodorderingapp.model;

public class HinhAnh {
    private String hinhAnhId;
    private String tenFile;
    private String sanPhamId;

    public HinhAnh() {
    }

    public HinhAnh(String hinhAnhId, String tenFile, String sanPhamId) {
        this.hinhAnhId = hinhAnhId;
        this.tenFile = tenFile;
        this.sanPhamId = sanPhamId;
    }

    public String getHinhAnhId() {
        return hinhAnhId;
    }

    public void setHinhAnhId(String hinhAnhId) {
        this.hinhAnhId = hinhAnhId;
    }

    public String getTenFile() {
        return tenFile;
    }

    public void setTenFile(String tenFile) {
        this.tenFile = tenFile;
    }

    public String getSanPhamId() {
        return sanPhamId;
    }

    public void setSanPhamId(String sanPhamId) {
        this.sanPhamId = sanPhamId;
    }
}
