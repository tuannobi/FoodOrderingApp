package com.foodorderingapp.model;

public class PhiShip {
    private String phiShipId;
    private String quan;
    private float chiPhi;

    public PhiShip() {
    }

    public PhiShip(String quan, float chiPhi) {
        this.quan = quan;
        this.chiPhi = chiPhi;
    }

    public PhiShip(String phiShipId, String quan, float chiPhi) {
        this.phiShipId = phiShipId;
        this.quan = quan;
        this.chiPhi = chiPhi;
    }

    public String getPhiShipId() {
        return phiShipId;
    }

    public void setPhiShipId(String phiShipId) {
        this.phiShipId = phiShipId;
    }

    public String getQuan() {
        return quan;
    }

    public void setQuan(String quan) {
        this.quan = quan;
    }

    public float getChiPhi() {
        return chiPhi;
    }

    public void setChiPhi(float chiPhi) {
        this.chiPhi = chiPhi;
    }
}
