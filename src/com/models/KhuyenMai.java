/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.models;

import java.math.BigDecimal;

/**
 *
 * @author user
 */
public class KhuyenMai {
    private String ID, TenKM, HinhThucKM, NgayBatDau, NgayKetThuc;
    private Double GiaTriGiam;
    private int Trangthai;

    public KhuyenMai() {
    }

    public KhuyenMai(String ID, String TenKM, String HinhThucKM, String NgayBatDau, String NgayKetThuc, Double GiaTriGiam, int Trangthai) {
        this.ID = ID;
        this.TenKM = TenKM;
        this.HinhThucKM = HinhThucKM;
        this.NgayBatDau = NgayBatDau;
        this.NgayKetThuc = NgayKetThuc;
        this.GiaTriGiam = GiaTriGiam;
        this.Trangthai = Trangthai;
    }

    public KhuyenMai(String TenKM, String HinhThucKM, String NgayBatDau, String NgayKetThuc, Double GiaTriGiam, int Trangthai) {
        this.TenKM = TenKM;
        this.HinhThucKM = HinhThucKM;
        this.NgayBatDau = NgayBatDau;
        this.NgayKetThuc = NgayKetThuc;
        this.GiaTriGiam = GiaTriGiam;
        this.Trangthai = Trangthai;
    }

    public int getTrangthai() {
        return Trangthai;
    }

    public void setTrangthai(int Trangthai) {
        this.Trangthai = Trangthai;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTenKM() {
        return TenKM;
    }

    public void setTenKM(String TenKM) {
        this.TenKM = TenKM;
    }

    public String getHinhThucKM() {
        return HinhThucKM;
    }

    public void setHinhThucKM(String HinhThucKM) {
        this.HinhThucKM = HinhThucKM;
    }

    public String getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(String NgayBatDau) {
        this.NgayBatDau = NgayBatDau;
    }

    public String getNgayKetThuc() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(String NgayKetThuc) {
        this.NgayKetThuc = NgayKetThuc;
    }

    public Double getGiaTriGiam() {
        return GiaTriGiam;
    }

    public void setGiaTriGiam(Double GiaTriGiam) {
        this.GiaTriGiam = GiaTriGiam;
    }

    @Override
    public String toString() {
        return TenKM;
    }

    
}
