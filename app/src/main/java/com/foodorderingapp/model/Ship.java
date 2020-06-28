package com.foodorderingapp.model;

public class Ship {
    private float phiShip;
    private String quan;
    private String soNha;
    public Ship() {

    }
    public Ship(float phiShip, String quan, String soNha) {
        this.phiShip = phiShip;
        this.quan = quan;
        this.soNha = soNha;
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
}
