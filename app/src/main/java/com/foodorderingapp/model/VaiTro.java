package com.foodorderingapp.model;

public class VaiTro {
    private String vaiTroId;
    private String moTa;

    public VaiTro() {
    }

    public VaiTro(String vaiTroId, String moTa) {
        this.vaiTroId = vaiTroId;
        this.moTa = moTa;
    }

    public String getVaiTroId() {
        return vaiTroId;
    }

    public void setVaiTroId(String vaiTroId) {
        this.vaiTroId = vaiTroId;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Override
    public String toString() {
        return "VaiTro{" +
                "vaiTroId='" + vaiTroId + '\'' +
                ", moTa='" + moTa + '\'' +
                '}';
    }
}
