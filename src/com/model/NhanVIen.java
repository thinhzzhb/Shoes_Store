/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

/**
 *
 * @author Admin
 */
public class NhanVIen {

    private int idNV;
    private String maNV;
    private String tenNV;
    private String diaChi;
    private String sdt;
    private ChucVu idCv;

    public NhanVIen(int idNV, String maNV, String tenNV, String diaChi, String sdt, ChucVu idCv) {
        this.idNV = idNV;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.idCv = idCv;
    }

    public NhanVIen() {
    }

    public int getIdNV() {
        return idNV;
    }

    public void setIdNV(int idNV) {
        this.idNV = idNV;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public ChucVu getIdCv() {
        return idCv;
    }

    public void setIdCv(ChucVu idCv) {
        this.idCv = idCv;
    }

    @Override
    public String toString() {
        return "NhanVIen{" + "idNV=" + idNV + ", maNV=" + maNV + ", tenNV=" + tenNV + ", diaChi=" + diaChi + ", sdt=" + sdt + ", idCv=" + idCv + '}';
    }

}
